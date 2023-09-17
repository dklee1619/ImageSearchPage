package com.example.imagesearchpage.data

import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.NetworkInterface
import java.util.concurrent.TimeUnit

object NetWorkClient {
    private const val DUST_BASE_URL = "" // 서비스 정보 : End Point
    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

//        if(Build.Config.DEBUG) // 계속 Import 안되면 지우기
            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        else
//            interceptor.level = HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .connectTimeout(20,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }
    private val dustRetrofit = Retrofit.Builder()
        .baseUrl(DUST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
            createOkHttpClient()
        ).build()
    val dustNetWork: NetworkInterface = dustRetrofit.create(NetworkInterface::class.java)
}