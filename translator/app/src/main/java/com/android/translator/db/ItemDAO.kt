package com.android.translator.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.translator.model.Item

@Dao
interface ItemDAO {
    @Insert
    fun saveItem(item: Item)

    @Query("SELECT * FROM  Item")
    fun getItems():List<Item>

    @Delete
    fun deleteItem(item: Item)

    @Query("DELETE FROM Item WHERE id == :id")
    fun deleteItemById(id: Int)
}