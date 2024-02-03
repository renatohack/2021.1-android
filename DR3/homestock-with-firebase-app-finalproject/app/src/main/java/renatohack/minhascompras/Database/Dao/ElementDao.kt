package renatohack.minhascompras.Database.Dao

import androidx.room.*
import renatohack.minhascompras.Model.Room.Element

@Dao
interface ElementDao {

    @Insert
    suspend fun insert(elemento : Element)

    @Delete
    suspend fun delete(elemento : Element)

    @Update
    suspend fun update(elemento: Element)

    // list
    @Query("SELECT * FROM Element")
    suspend fun listAll(): List<Element>

    @Query("SELECT * FROM Element WHERE id = :elementID")
    suspend fun selectElement(elementID: String) : Element

}