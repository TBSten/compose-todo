package dev.tbsten.composetodo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tbsten.composetodo.data.TodoRepository
import dev.tbsten.composetodo.domain.Todo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

// HomeScreenで発生する処理を記述
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val todoRepository: TodoRepository,
) : ViewModel() {

  private val _todos = MutableStateFlow<List<Todo>?>(null)
  val todos = _todos.asStateFlow()

  init {
    refreshTodos()
  }

  fun refreshTodos() {
    viewModelScope.launch {
      _todos.update { todoRepository.getAllTodo() }
    }
  }

  fun addNewTodo() {
    viewModelScope.launch {
      val newTodo = Todo(
        id = 0,
        title = "無題",
        details = "TODOの説明",
        createAt = LocalDateTime.now(),
        updateAt = LocalDateTime.now(),
      )
      todoRepository.add(newTodo)
    }.withRefresh()
  }

  fun updateTodo(todo: Todo) {
    viewModelScope.launch {
      todoRepository.update(todo)
    }.withRefresh()
  }

  fun deleteTodo(todo: Todo) {
    viewModelScope.launch {
      todoRepository.delete(todo)
    }.withRefresh()
  }

  private fun Job.withRefresh(): Job {
    invokeOnCompletion {
      refreshTodos()
    }
    return this
  }

}