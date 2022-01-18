package de.fayssal.codingchallenge.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fayssal.codingchallenge.data.LevelDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    //ah yes, the reason people normally use Dependency Injection
    private val levelDataRepository: LevelDataRepository = LevelDataRepository()

    private val _latestLevelChanges = MutableStateFlow<ArrayList<Int>>(ArrayList())
    val latestLevelChanges = _latestLevelChanges.asStateFlow()

    init {
        viewModelScope.launch {
            levelDataRepository.lastLevelChanges.collect { changes ->
                _latestLevelChanges.value = changes
            }
        }
    }
}
