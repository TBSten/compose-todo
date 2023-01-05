package dev.tbsten.composetodo.detail

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import dev.tbsten.composetodo.data.TodoRepository
import dev.tbsten.composetodo.domain.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
  @Assisted private val todoId: Long?,
  private val todoRepository: TodoRepository,
) : ViewModel() {
  private val _todo = MutableStateFlow<Todo?>(null)
  val todo = _todo.asStateFlow()

  init {
    refresh()
  }

  fun refresh() {
    viewModelScope.launch {
      if (todoId == null) return@launch
      val todo = todoRepository.getTodo(todoId)
      _todo.update { todo }
    }
  }

}


@AssistedFactory
interface DetailViewModelFactory {
  fun create(todoId: Long?): DetailViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface DetailViewModelFactoryProvider {
  fun factory(): DetailViewModelFactory
}

@Composable
fun detailViewModel(todoId: Long?): DetailViewModel {
  val factory = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    DetailViewModelFactoryProvider::class.java,
  ).factory()
  return viewModel(key = "$todoId") { factory.create(todoId) }
}
