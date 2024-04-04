package com.best.data.api

import nti.team.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    val api: ApiInterface = Retrofit.Builder()
        .baseUrl("https://anika1d.github.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiInterface::class.java)
}