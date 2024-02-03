package renatohack.minhascompras.Database.Dao

import androidx.room.*
import renatohack.minhascompras.Model.Room.ListElement
import renatohack.minhascompras.Model.Room.Lista


@Dao
interface ListDao {

    @Insert
    suspend fun insert(lista : Lista)

    @Delete
    suspend fun delete(lista : Lista)

    @Update
    suspend fun update(lista: Lista)

    // list
    @Query("SELECT * FROM Lista")
    suspend fun listAll(): MutableList<Lista>

    @Query("SELECT * FROM Lista ORDER BY id DESC LIMIT 1")
    suspend fun getLastList() : Lista?

    @Query("SELECT * FROM Lista WHERE id = :userId")
    suspend fun getListAndElementsByListId(userId : String) : ListElement

    @Query("SELECT * FROM Lista")
    suspend fun getAllListAndElements() : List<ListElement>

    @Query("DELETE FROM Element WHERE listID = :listID" )
    suspend fun deleteElementsByListID(listID : String)

    @Query("SELECT * FROM Lista WHERE id = :listID")
    suspend fun selectList(listID: String) : Lista



}