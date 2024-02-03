package com.renatohack.renato_hack_dr4_at.CadastroAnotacao

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.renatohack.renato_hack_dr4_at.MainActivity
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CadastroAnotacaoViewModel(context: Context, activity: MainActivity) : ViewModel() {

    val contextMainActivity = context
    val mainActivity = activity

    val REQUEST_PERMISSIONS_CODE = 128

    private val _latitude = MutableLiveData<String>()
    val latitude: LiveData<String> = _latitude
    private val _longitude = MutableLiveData<String>()
    val longitude: LiveData<String> = _longitude

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init{
        _status.value = false
    }

    // GEOLOCALIZACAO XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    fun callAccessLocation() {
        val permissionAFL = ContextCompat.checkSelfPermission(contextMainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionACL = ContextCompat.checkSelfPermission(contextMainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permissionAFL != PackageManager.PERMISSION_GRANTED && permissionACL != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                callDialog("É preciso liberar ACCESS_FINE_LOCATION", arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            }
            else {
                ActivityCompat.requestPermissions(mainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE)
            }
        }
        else {
            readMyCurrentCoordinates()
        }
    }

    private fun readMyCurrentCoordinates() {
        val locationManager = contextMainActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetworkEnabled) {
            Log.d("Permissao", "Ative os serviços necessários")
        }
        else {
            if (isGPSEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0L, 0f, locationListener)
                } catch(ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            }
            else if (isNetworkEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1L, 0f, locationListener)
                } catch(ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            }
        }
    }

    private val locationListener: LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {

            _latitude.value = location.latitude.toString()
            _longitude.value = location.longitude.toString()
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}

    }

    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


    // UTILIDADE XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private fun callDialog(mensagem: String, permissions: Array<String>) {
        var mDialog = AlertDialog.Builder(contextMainActivity)
            .setTitle("Permissão")
            .setMessage(mensagem)
            .setPositiveButton("Ok")
            { dialog, id ->
                ActivityCompat.requestPermissions(
                    mainActivity, permissions,
                    REQUEST_PERMISSIONS_CODE)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel")
            { dialog, id ->
                dialog.dismiss()
            }
        mDialog.show()
    }

    private fun getDateAndHour(): String {
        val currentDataHour = LocalDateTime.now()

        return currentDataHour.format(DateTimeFormatter.ISO_DATE)
    }


    private fun getEncFile(nome: String): EncryptedFile{
        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val file = File(contextMainActivity.filesDir, nome)
        return EncryptedFile.Builder(
            file,
            contextMainActivity,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB)
            .build()
    }

    fun clickGravar(titulo : String, anotacao : String, imagem : Bitmap, data : String = getDateAndHour()){

            if (!File(contextMainActivity.filesDir, "${titulo.uppercase()}($data).txt").exists()) {
                val encryptedOut: FileOutputStream = getEncFile("${titulo.uppercase()}($data).txt").openFileOutput()
                val pw = PrintWriter(encryptedOut)
                pw.println("Latitude: ${_latitude.value}\nLongitude: ${_longitude.value}\n\n$anotacao")
                pw.flush()
                encryptedOut.close()

                val encryptedOutPhoto: FileOutputStream = getEncFile("${titulo.uppercase()}($data).fig").openFileOutput()
                val pwPhoto = PrintWriter(encryptedOut)
                pwPhoto.println(imagem.compress(Bitmap.CompressFormat.JPEG, 85, encryptedOutPhoto))
                pwPhoto.flush()
                encryptedOut.close()

                _status.value = true
            }
        }

}