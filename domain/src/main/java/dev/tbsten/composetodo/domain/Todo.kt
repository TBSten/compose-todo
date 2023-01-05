package dev.tbsten.composetodo.domain

import java.time.LocalDateTime

data class Todo(
  val id: Long,
  val title: String,
  val details: String,
  val createAt: LocalDateTime,
  val updateAt: LocalDateTime,
)

