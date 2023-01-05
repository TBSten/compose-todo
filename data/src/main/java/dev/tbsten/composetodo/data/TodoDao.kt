package dev.tbsten.composetodo.data

import androidx.room.*

@Dao
interface TodoDao {

  @Query("SELECT * FROM todos")
  suspend fun getAll(): List<TodoEntity>

  @Query("SELECT * FROM todos WHERE id = :id")
  suspend fun getById(id: Long): TodoEntity

  @Insert
  suspend fun insert(todo: TodoEntity): Long

  @Update
  suspend fun update(todo: TodoEntity)

  @Delete
  suspend fun delete(todo: TodoEntity)

}