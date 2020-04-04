package com.android.midtermproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.midtermproject.model.ToDoItem

@Dao
interface ToDoItemDAO {
    @Insert
    fun saveToDoItem(todo: ToDoItem)

    @Query("SELECT * FROM ToDoItem")
    fun getGenders(): List<ToDoItem>

    @Update
    fun updateToDo(todo: ToDoItem)

    @Query("SELECT * FROM ToDoItem WHERE id = :id")
    fun getItemById(id:Int): ToDoItem

}