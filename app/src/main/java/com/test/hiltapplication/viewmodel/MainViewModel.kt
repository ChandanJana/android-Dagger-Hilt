package com.test.hiltapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.hiltapplication.data.Repository
import com.test.hiltapplication.data.local.ImageTable
import com.test.hiltapplication.model.DogResponse
import com.test.hiltapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ImageTable?>> = MutableLiveData()
    val response: LiveData<NetworkResult<ImageTable?>> = _response

    fun fetchDogResponse() = viewModelScope.launch {
        repository.getDog().collect { values ->
            if (values.data != null){
                var ii = repository.saveImage(values.data.message)
                _response.value = NetworkResult.success(ii)
            }

        }
    }

}