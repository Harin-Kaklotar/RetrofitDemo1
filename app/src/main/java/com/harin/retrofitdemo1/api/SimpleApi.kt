package com.harin.retrofitdemo1.api

import com.harin.retrofitdemo1.model.Post
import retrofit2.Response
import retrofit2.http.*

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

    // get list using custom query params
    @GET("posts")
    suspend fun getPost3(@Query("userId") userId: Int): Response<List<Post>>

    // get list using custom multiple query params
    @GET("posts")
    suspend fun getPost4(@Query("userId") userId: Int, @Query("id") id: Int): Response<List<Post>>

    // get list using QueryMap
    @GET("posts")
    suspend fun getPost5(@Query("userId") userId: Int, @QueryMap params: Map<String, String>): Response<List<Post>>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost1(@Field("userId") userId: Int, @Field("id") id: Int,
        @Field("title") title: String, @Field("body") body: String): Response<Post>

    @POST("posts")
    suspend fun pushPost2(@Body post: Post): Response<Post>
}