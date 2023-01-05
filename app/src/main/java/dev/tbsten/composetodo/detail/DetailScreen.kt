package dev.tbsten.composetodo.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
  todoId: Long?,
  detailViewModel: DetailViewModel = detailViewModel(todoId),
) {
  val _todo by detailViewModel.todo.collectAsState()
  val todo = _todo

  if (todo == null) {
    CircularProgressIndicator()
    return
  }

  Scaffold(
    topBar = { DetailTopBar(todo.title) },
  ) { paddingValues ->
    Box(modifier = Modifier.padding(paddingValues)) {
      Text(todo.details)
    }
  }

}

@Composable
fun DetailTopBar(
  todoTitle: String,
) {
  TopAppBar(
    title = {
      Text(todoTitle)
    },
    actions = {
      // ボタン
    },
  )
}
