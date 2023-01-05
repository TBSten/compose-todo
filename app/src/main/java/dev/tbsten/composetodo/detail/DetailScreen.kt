package dev.tbsten.composetodo.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
  todoId: Long?,
) {
  Text("detail page of $todoId")
}
