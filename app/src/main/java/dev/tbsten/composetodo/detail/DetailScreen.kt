package dev.tbsten.composetodo.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
fun DetailScreen(
  todoId: Long?,
  detailViewModel: DetailViewModel = detailViewModel(todoId),
) {
  val _todo by detailViewModel.todo.collectAsState()
  val todo = _todo
  val isEditMode by detailViewModel.isEditMode.collectAsState()

  if (todo == null) {
    CircularProgressIndicator()
    return
  }

  Scaffold(
    topBar = {
      DetailTopBar(
        todo.title,
        isEditMode,
        onSwitchToEditMode = { detailViewModel.switchToEditMode() },
        onEditTitle = { detailViewModel.editTodo(todo.copy(title = it)) },
        onSave = { detailViewModel.save() },
      )
    },
  ) { paddingValues ->
    Box(modifier = Modifier.padding(paddingValues)) {
      if (!isEditMode) {
        Text(todo.details)
      } else {
        TextField(
          value = todo.details,
          onValueChange = { detailViewModel.editTodo(todo.copy(details = it)) },
          modifier = Modifier.fillMaxSize(),
        )
      }
    }
  }

}

@Composable
fun DetailTopBar(
  todoTitle: String,
  isEditMode: Boolean,
  onEditTitle: (String) -> Unit,
  onSave: () -> Unit,
  onSwitchToEditMode: () -> Unit,
) {
  TopAppBar(
    title = {
      if (!isEditMode) {
        // 表示モード
        Text(todoTitle)
      } else {
        // 編集モード
        TextField(
          value = todoTitle,
          onValueChange = { onEditTitle(it) },
        )
      }
    },
    actions = {
      if (!isEditMode) {
        IconButton(onClick = { onSwitchToEditMode() }) {
          Icon(rememberVectorPainter(Icons.Default.Edit), "Todoを編集")
        }
      } else {
        IconButton(onClick = { onSave() }) {
          Icon(rememberVectorPainter(Icons.Default.Check), "Todoを保存")
        }
      }
    },
  )
}
