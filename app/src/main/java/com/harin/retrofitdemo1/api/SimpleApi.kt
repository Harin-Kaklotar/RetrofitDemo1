package com.harin.retrofitdemo1.api

import com.harin.retrofitdemo1.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    // this will not gave error response
    @GET("posts/1")
    suspend fun getPost() : Post

    // this will provide error response
    @GET("posts/1")
    suspend fun getPostResponse() : Response<Post>

    // with dynamic id
    @GET("posts/{id}")
    suspend fun getPost2(@Path("id") id: Int): Response<Post>
}