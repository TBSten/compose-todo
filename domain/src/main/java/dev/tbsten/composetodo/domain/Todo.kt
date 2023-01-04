package dev.tbsten.composetodo.domain

import java.util.*

data class Todo(
  val id: Long,
  val title: String,
  val details: String,
  val createAt: Date,
  val updateAt: Date,
)

