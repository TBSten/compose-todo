package dev.tbsten.composetodo.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tbsten.composetodo.data.TodoRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val todoRepository: TodoRepository,
) : ViewModel() {
  // HomeScreenで発生する処理を記述
}