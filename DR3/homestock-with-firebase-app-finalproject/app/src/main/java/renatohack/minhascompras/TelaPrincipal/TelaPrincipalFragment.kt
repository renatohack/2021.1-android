package renatohack.minhascompras.TelaPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.facebook.login.LoginManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_tela_principal.*
import renatohack.minhascompras.R


class TelaPrincipalFragment : Fragment() {

    private lateinit var buttonFazerNovaLista : Button
    private lateinit var buttonListasAbertas : Button
    private lateinit var buttonLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_tela_principal, container, false)

        setViews(view)
        setListeners()

        MobileAds.initialize(requireContext()){}

//        val adView = AdView(requireContext())
//        adView.adSize = AdSize.BANNER
//        adView.adUnitId = "ca-app-pub-2641943871350451/6795895250"
//
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)

        var mAdView = view.findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return view
    }

    private fun setListeners() {
        buttonFazerNovaLista.setOnClickListener {
            val nav = findNavController()
            nav.navigate(R.id.action_telaPrincipalFragment_to_fazerNovaLista)
        }

        buttonListasAbertas.setOnClickListener {
            val nav = findNavController()
            nav.navigate(R.id.action_telaPrincipalFragment_to_listasAbertas)
        }

        buttonLogout.setOnClickListener {
            Firebase.auth.signOut()
            LoginManager.getInstance().logOut()
            val nav = findNavController()
            nav.navigate(R.id.action_telaPrincipalFragment_to_loginFragment)
        }
    }

    private fun setViews(view : View){
        buttonFazerNovaLista = view.findViewById(R.id.buttonFazerNovaLista)
        buttonListasAbertas = view.findViewById(R.id.buttonListasAbertas)
        buttonLogout = view.findViewById(R.id.buttonLogout)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}