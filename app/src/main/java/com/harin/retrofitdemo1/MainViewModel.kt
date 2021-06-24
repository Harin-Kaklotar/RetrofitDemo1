package com.harin.retrofitdemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harin.retrofitdemo1.model.Post
import com.harin.retrofitdemo1.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var response: MutableLiveData<Post> = MutableLiveData()
    var postResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    var post2Response: MutableLiveData<Response<Post>> = MutableLiveData()

    private var isProcess : MutableLiveData<Boolean> = MutableLiveData()
    private var showProgress: LiveData<Boolean> = isProcess

    fun isShowProgress(): LiveData<Boolean>{
       return showProgress
    }

    fun getPost(){
        isProcess.value = true
        viewModelScope.launch {
           val post = repository.getPost()
            isProcess.value = false
            response.value = post
        }
    }

    fun getPostResponse(){
        isProcess.value = true
        viewModelScope.launch {
            val response = repository.getPostResponse()
            isProcess.value = false
            postResponse.value = response
        }
    }

    fun getPost2(id: Int){
        viewModelScope.launch {
            isProcess.value = true
            val p2Response = repository.getPost2(id)
            isProcess.value = false
            post2Response.value = p2Response
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}