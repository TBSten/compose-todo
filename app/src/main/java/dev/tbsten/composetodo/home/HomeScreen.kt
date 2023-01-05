package dev.tbsten.composetodo.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.tbsten.composetodo.domain.Todo
import java.time.LocalDateTime

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel = hiltViewModel(),
  gotoDetail: (Long) -> Unit,
) {
  val todos by homeViewModel.todos.collectAsState()

  Scaffold(
    topBar = { HomeTopBar() }
  ) {
    LazyColumn {
      item {
        Button(onClick = { homeViewModel.addNewTodo() }) {
          Text("追加")
        }
      }

      val _todos = todos
      if (_todos != null) {
        if (_todos.isEmpty()) {
          item {
            Text("TODOがまだありません")
          }
        }
        // todoがnullじゃないならそれを一覧表示
        items(_todos) { todo ->
          TodoListItem(
            todo,
            onDelete = { homeViewModel.deleteTodo(todo) },
            onClick = { gotoDetail(todo.id) },
          )
        }
      } else {
        // todosがnullならロード中
        item {
          CircularProgressIndicator()
        }
      }
    }
  }
}

@Composable
fun HomeTopBar() {
  TopAppBar { Text("ホーム") }
}

@Composable
fun TodoListItem(
  todo: Todo,
  onDelete: () -> Unit,
  onClick: () -> Unit,
) {
  Row(
    modifier = Modifier.fillMaxWidth().clickable { onClick() },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text("${todo.title}", modifier = Modifier.weight(1F))
    IconButton(onClick = { onDelete() }) {
      Icon(rememberVectorPainter(Icons.Default.Delete), "削除")
    }
  }
}

@Preview(widthDp = 300)
@Preview(widthDp = 200)
@Preview(widthDp = 100)
@Composable
fun TodoListItemPreview() {
  val exampleTodo = Todo(
    id = 0,
    title = "test",
    details = "test test test",
    createAt = LocalDateTime.now(),
    updateAt = LocalDateTime.now(),
  )

  TodoListItem(exampleTodo, {}, {})
}

