package com.harin.retrofitdemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harin.retrofitdemo1.model.Post
import com.harin.retrofitdemo1.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var response: MutableLiveData<Post> = MutableLiveData()
    var postResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    var post2Response: MutableLiveData<Response<Post>> = MutableLiveData()
    var post3Response: MutableLiveData<Response<List<Post>>> =
        MutableLiveData<Response<List<Post>>>()
    var post4Response: MutableLiveData<Response<List<Post>>> =
        MutableLiveData<Response<List<Post>>>()
    var post5Response: MutableLiveData<Response<List<Post>>> =
        MutableLiveData<Response<List<Post>>>()
    var pushPost1Response: MutableLiveData<Response<Post>> = MutableLiveData<Response<Post>>()

    private var isProcess: MutableLiveData<Boolean> = MutableLiveData()
    private var showProgress: LiveData<Boolean> = isProcess

    fun isShowProgress(): LiveData<Boolean> {
        return showProgress
    }

    fun getPost() {
        isProcess.value = true
        viewModelScope.launch {
            val post = repository.getPost()
            isProcess.value = false
            response.value = post
        }
    }

    fun getPostResponse() {
        isProcess.value = true
        viewModelScope.launch {
            val response = repository.getPostResponse()
            isProcess.value = false
            postResponse.value = response
        }
    }

    fun getPost2(id: Int) {
        viewModelScope.launch {
            isProcess.value = true
            val p2Response = repository.getPost2(id)
            isProcess.value = false
            post2Response.value = p2Response
        }
    }

    fun getPost3(userId: Int) {
        viewModelScope.launch {
            isProcess.value = true
            val p3Response = repository.getPost3(userId)
            isProcess.value = false
            post3Response.value = p3Response
        }
    }

    fun getPost4(userId: Int, id: Int) {
        viewModelScope.launch {
            isProcess.value = true
            val p4Response = repository.getPost4(userId, id)
            isProcess.value = false
            post4Response.value = p4Response
        }
    }

    fun getPost5(userId: Int, params: Map<String, String>) {
        viewModelScope.launch {
            isProcess.value = true
            val p5Response = repository.getPost5(userId, params)
            isProcess.value = false
            post5Response.value = p5Response
        }
    }

    fun pushPost1(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            isProcess.value = true
            val pp1Response = repository.pushPost1(userId, id, title, body)
            isProcess.value = false
            pushPost1Response.value = pp1Response
        }
    }

    fun pushPost2(post: Post) {
        viewModelScope.launch {
            isProcess.value = true
            val pp1Response = repository.pushPost2(post)
            isProcess.value = false
            pushPost1Response.value = pp1Response
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}