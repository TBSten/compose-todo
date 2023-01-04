package dev.tbsten.composetodo.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel = hiltViewModel(),
) {
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
