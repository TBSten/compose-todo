package dev.tbsten.composetodo.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
  Scaffold(
    topBar = { HomeTopBar() }
  ) {
    Text("home screen")
  }
}

@Composable
fun HomeTopBar() {
  TopAppBar { Text("ホーム") }
}
