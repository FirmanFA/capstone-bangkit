package com.bangkit.capsstonebangkit.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.ProfileResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class DashboardViewModel(private val repository: UserRepository): ViewModel() {

    private val _profileResponse = MutableLiveData<Resource<Response<ProfileResponse>>>()
    val profileResponse: LiveData<Resource<Response<ProfileResponse>>> get() = _profileResponse

    fun getProfile(){

        viewModelScope.launch {
            _profileResponse.postValue(Resource.loading())
            try {
                val dataProfile = repository.getProfile()
                val successResource = Resource.success(dataProfile)
                _profileResponse.postValue(successResource)
            }catch (exp: Exception){
                _profileResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}