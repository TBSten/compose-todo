package dev.tbsten.composetodo.data

import dev.tbsten.composetodo.domain.Todo
import javax.inject.Inject

// Todoの入出力をこのクラスに集約
class TodoRepository @Inject constructor(
  private val db: AppDatabase,
) {
  suspend fun getAllTodo() = db.todoDao().getAll().toTodos()
  suspend fun getTodo(todoId: Long) = db.todoDao().getById(todoId)
  suspend fun add(todo: Todo) = db.todoDao().insert(todo.toEntity())
  suspend fun update(todo: Todo) = db.todoDao().update(todo.toEntity())
  suspend fun delete(todo: Todo) = db.todoDao().delete(todo.toEntity())
}