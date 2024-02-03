package renatohack.minhascompras.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import renatohack.minhascompras.Model.Room.TempElements

@Dao
interface TempDao {

    @Insert
    suspend fun insert(temp : TempElements)

    @Delete
    suspend fun delete(temp : TempElements)

    // list
    @Query("SELECT * FROM TempElements")
    suspend fun listAll(): MutableList<TempElements>

    @Query("DELETE FROM TempElements")
    suspend fun deleteAllTemps()

}