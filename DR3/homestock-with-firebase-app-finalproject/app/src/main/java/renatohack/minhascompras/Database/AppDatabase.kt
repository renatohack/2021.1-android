package renatohack.minhascompras.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Database.Dao.TempDao
import renatohack.minhascompras.Model.Room.Element
import renatohack.minhascompras.Model.Room.Lista
import renatohack.minhascompras.Model.Room.TempElements


@Database(entities = [Lista::class, Element::class, TempElements::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ListaDao(): ListDao
    abstract fun ElementosDao(): ElementDao
    abstract fun tempDao(): TempDao

    companion object {
        // uma variavel do tipo AppDatabase
        private var INSTANCE: AppDatabase? = null

        // um metodo que retorna AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null)
                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database.db"
                    ).build()
            return INSTANCE as AppDatabase
        }
    }
}