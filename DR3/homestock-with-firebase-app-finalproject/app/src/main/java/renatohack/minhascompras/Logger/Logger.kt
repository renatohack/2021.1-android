package renatohack.minhascompras.Logger

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class Logger(val context: Context) {

    val listas = "listas.txt"

    fun write(msg: String){
        val fos : FileOutputStream = context.openFileOutput(listas, Context.MODE_PRIVATE)
        fos.write(msg.toByteArray())
    }

    fun read(): String {
        val fis = context.openFileInput(listas)
        val br = fis.bufferedReader()
        return br.readText()
    }

    companion object {                                  // como se fosse atributos e metodos estaticos
        private var instance: Logger? = null

        fun getInstance(context: Context): Logger {
            if (instance == null){
                instance = Logger(context)
            }
            return instance as Logger
        }
    }
}