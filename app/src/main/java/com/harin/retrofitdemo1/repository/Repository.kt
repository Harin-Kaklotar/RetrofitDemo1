package com.harin.retrofitdemo1.repository

import com.harin.retrofitdemo1.api.RetrofitInstance
import com.harin.retrofitdemo1.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Post{
       return RetrofitInstance.api.getPost()
    }

    suspend fun getPostResponse(): Response<Post>{
        return RetrofitInstance.api.getPostResponse()
    }

    suspend fun getPost2(id: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(id)
    }

    suspend fun getPost3(userId: Int): Response<List<Post>>{
        return RetrofitInstance.api.getPost3(userId)
    }
}