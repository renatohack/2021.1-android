package com.renatohack.renato_hack_dr4_at.TelaPrincipal

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.renatohack.renato_hack_dr4_at.MainActivity
import com.renatohack.renato_hack_dr4_at.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TelaPrincipalFragment : Fragment() ,
    BillingClientStateListener ,
    SkuDetailsResponseListener,
    PurchasesUpdatedListener ,
    ConsumeResponseListener  {

    private lateinit var viewModel: TelaPrincipalViewModel
    private lateinit var buttonCadastrar: Button
    private lateinit var buttonListagem: Button
    private lateinit var buttonBilling : Button
    private lateinit var mAdView: AdView
    private lateinit var clienteInApp: BillingClient

    val REQUEST_PERMISSIONS_CODE = 128

    private var mapSku = HashMap<String,SkuDetails>()
    private var currentSku = "android.test.purchased"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clienteInApp = BillingClient.newBuilder(requireActivity())
            .enablePendingPurchases()
            .setListener(this)
            .build()
        clienteInApp.startConnection(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        clienteInApp = BillingClient.newBuilder(requireContext())
//            .enablePendingPurchases()
//            .setListener(this)
//            .build()
//        clienteInApp.startConnection(this)

        val view = inflater.inflate(R.layout.tela_principal_fragment, container, false)

        buttonCadastrar = view.findViewById(R.id.buttonCriarAnotacaoTelaPrincipal)
        buttonListagem = view.findViewById(R.id.buttonAnotacoesSalvasTelaPrincipal)
        buttonBilling = view.findViewById(R.id.buttonBilling)

        buttonCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_telaPrincipalFragment_to_cadastroAnotacaoFragment)
        }

        buttonListagem.setOnClickListener {
            if(callReadFromSDCard()) findNavController().navigate(R.id.action_telaPrincipalFragment_to_listagemFragment)
        }

        buttonBilling.setOnClickListener {
            processGoogleInApp(view)
        }

        MobileAds.initialize(requireContext()){}

        mAdView = view.findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return view
    }

    override fun onDestroy() {
        clienteInApp.endConnection()
        super.onDestroy()
    }


    override fun onBillingSetupFinished(
        billingResult: BillingResult) {
        if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
            Log.d("COMPRA>>","Serviço InApp conectado")
            val skuList = arrayListOf(currentSku)
            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skuList).setType(
                BillingClient.SkuType.INAPP)
            clienteInApp.querySkuDetailsAsync(params.build(), this)
        }
    }

    override fun onBillingServiceDisconnected() {
        Log.d("COMPRA>>","Serviço InApp desconectado")
    }


    override fun onSkuDetailsResponse(
        billingResult: BillingResult,
        skuDetailsList: MutableList<SkuDetails>?) {
        if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
            mapSku.clear()
            skuDetailsList?.forEach{
                    t ->
                val preco = t.price
                val descricao = t.description
                mapSku[t.sku] = t
                Log.d("COMPRA>>",
                    "Produto Disponivel ($preco): $descricao")
            }
        }
        atualizarBotao()
    }

    fun processGoogleInApp(view: View){
        // O valor é definido no Google Dev Console
        // Toda a transação é controlada no ambiente do Google
        val skuDetails = mapSku[currentSku]
        val purchaseParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuDetails!!).build()
        clienteInApp.launchBillingFlow(requireActivity(), purchaseParams)
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: List<Purchase>?) {
        if (billingResult.responseCode ==
            BillingClient.BillingResponseCode.OK &&
            purchases != null) {
            for (purchase in purchases) {
                GlobalScope.launch(Dispatchers.IO){
                    handlePurchase(purchase)
                }
            }
        } else if (billingResult.responseCode ==
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            Log.d("COMPRA>>","Produto já foi comprado")
        } else if (billingResult.responseCode ==
            BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.d("COMPRA>>","Usuário cancelou a compra")
        } else {
            Log.d("COMPRA>>",
                "Código de erro desconhecido: ${billingResult.responseCode}")
        }
    }

    suspend fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED)
        {
            // Aqui acessaria a base e concederia o produto ao usuário
            Log.d("COMPRA>>","Produto obtido com sucesso")
            // Acknowledge -> Obrigatório para confirmação ao Google
            if (!purchase.isAcknowledged) {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams
                    .newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                val ackPurchaseResult = withContext(Dispatchers.IO) {
                    clienteInApp.acknowledgePurchase(
                        acknowledgePurchaseParams.build())
                }
            }
            atualizarBotao()
        }
    }

    fun consumeGoogleInApp(view: View) {
        var compras = clienteInApp.queryPurchases(
            BillingClient.SkuType.INAPP)
        if( compras.purchasesList!!.size > 0 )
        {
            var purchase: Purchase = compras.purchasesList!!.get(0)
            var params = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            clienteInApp.consumeAsync(params,this)
        }
    }

    override fun onConsumeResponse(billingResult: BillingResult, purchaseToken: String) {
        if(billingResult.responseCode==
            BillingClient.BillingResponseCode.OK) {
            Log.d("COMPRA>>", "Produto Consumido")
        }
        atualizarBotao()
    }

    private fun atualizarBotao(){
        var compras = clienteInApp.queryPurchases(BillingClient.SkuType.INAPP)
        if( compras.purchasesList!!.size > 0 )
        {
            buttonBilling.visibility = View.GONE
            mAdView.visibility = View.GONE
        }
    }

    fun callReadFromSDCard(): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                callDialog(
                    "É preciso a liberar READ_EXTERNAL_STORAGE",
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                )
                return false
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )

                return false
            }
        }
        else {
            return true
        }
    }

    fun callDialog(mensagem: String, permissions: Array<String>) {
        val mDialog = AlertDialog.Builder(requireContext())
            .setTitle("Permissão")
            .setMessage(mensagem)
            .setPositiveButton("Ok")
            { dialog, id ->
                ActivityCompat.requestPermissions(
                    requireActivity(), permissions,
                    REQUEST_PERMISSIONS_CODE)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel")
            { dialog, id ->
                dialog.dismiss()
            }
        mDialog.show()
    }

}