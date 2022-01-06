package de.fayssal.codingchallenge.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class LevelDataSource {

    val latestLevelChange: Flow<Int> = flow {
        while (true) {
            val latestChange = (0..32).random()
            emit(latestChange)
            kotlinx.coroutines.delay(1500)
        }
    }
}

class LevelDataRepository(

) {
    private val levelDataSource = LevelDataSource()

    private val _levelList: MutableLiveData<ArrayList<Int>> = MutableLiveData(ArrayList())

    val lastLevelChanges: Flow<ArrayList<Int>> =
        levelDataSource.latestLevelChange
            .map {
                if (_levelList.value!!.size > 7) _levelList.value!!.removeAt(0)
                _levelList.value?.add(it)
                _levelList.value!!
            }


}