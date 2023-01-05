package dev.tbsten.composetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.tbsten.composetodo.detail.DetailScreen
import dev.tbsten.composetodo.home.HomeScreen
import dev.tbsten.composetodo.ui.theme.ComposeTodoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeTodoTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          AppRoot()
        }
      }
    }
  }
}

@Composable
fun AppRoot() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable("home") {
      HomeScreen(
        gotoDetail = { todoId -> navController.navigate("detail/$todoId") },
      )
    }
    composable(
      "detail/{todoId}",
      arguments = listOf(
        navArgument("todoId") { type = NavType.LongType },
      ),
    ) {
      val todoId = it.arguments?.getLong("todoId")
      DetailScreen(todoId)
    }
  }
}

