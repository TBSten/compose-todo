package dev.tbsten.composetodo.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel = hiltViewModel(),
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
          Text("${todo.title}")
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
