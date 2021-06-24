package com.harin.retrofitdemo1.repository

import com.harin.retrofitdemo1.api.RetrofitInstance
import com.harin.retrofitdemo1.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPostResponse(): Response<Post> {
        return RetrofitInstance.api.getPostResponse()
    }

    suspend fun getPost2(id: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(id)
    }

    suspend fun getPost3(userId: Int): Response<List<Post>> {
        return RetrofitInstance.api.getPost3(userId)
    }

    suspend fun getPost4(userId: Int, id: Int): Response<List<Post>> {
        return RetrofitInstance.api.getPost4(userId, id)
    }

    suspend fun getPost5(userId: Int, params: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getPost5(userId, params)
    }

    suspend fun pushPost1(
        userId: Int, id: Int,
        title: String, body: String
    ): Response<Post> {
        return RetrofitInstance.api.pushPost1(userId, id, title, body)
    }
}