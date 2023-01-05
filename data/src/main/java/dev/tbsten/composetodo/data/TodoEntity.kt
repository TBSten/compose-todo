package dev.tbsten.composetodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.tbsten.composetodo.domain.Todo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

@Entity(tableName = "todos")
data class TodoEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val title: String,
  val details: String,
  val createAt: String,
  val updateAt: String,
)

fun TodoEntity.toTodo() = Todo(
  id = id,
  title = title,
  details = details,
  createAt = LocalDateTime.parse(createAt, dateTimeFormatter),
  updateAt = LocalDateTime.parse(createAt, dateTimeFormatter),
)

fun List<TodoEntity>.toTodos(): List<Todo> = this.map { it.toTodo() }

fun Todo.toEntity() = TodoEntity(
  id = id,
  title = title,
  details = details,
  createAt = createAt.format(dateTimeFormatter),
  updateAt = updateAt.format(dateTimeFormatter),
)

fun List<Todo>.toEntities(): List<TodoEntity> = this.map { it.toEntity() }
