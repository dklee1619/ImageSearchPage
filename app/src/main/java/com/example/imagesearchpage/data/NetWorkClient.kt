package com.example.imagesearchpage.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.NetworkInterface
import java.util.concurrent.TimeUnit

object NetWorkClient {
    // 기본 URL로 사용될 엔드포인트를 상수로 정의
    private const val BASE_URL = "https://dapi.kakao.com/"

    // OkHttpClient를 생성하는 함수
    // OkHttpClient는 HTTP 요청과 응답을 처리하는 라이브러리로, Retrofit과 함께 사용됩니다.
    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        // 로깅 인터셉터를 생성합니다. 이는 요청/응답의 로그를 확인하기 위해 사용됩니다.
        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "KakaoAK 428f408df29c019a8991a06a6d291b12")
                .build()
            chain.proceed(request)
        }

        // 개발 모드일 때만 BODY 로그를 출력합니다. (이 코드에서는 주석 처리되어 있습니다.)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // OkHttpClient.Builder를 사용하여 OkHttpClient의 인스턴스를 생성합니다.
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)  // 연결 타임아웃을 20초로 설정
            .readTimeout(20, TimeUnit.SECONDS)     // 읽기 타임아웃을 20초로 설정
            .writeTimeout(20, TimeUnit.SECONDS)    // 쓰기 타임아웃을 20초로 설정
            .addInterceptor(loggingInterceptor) // 로깅 인터셉터 추가
            .addInterceptor(authInterceptor)   // Kakao 인증 인터셉터 추가
            .build()
    }

    // Retrofit 인스턴스를 생성합니다.
    private val imageRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)                            // 기본 URL을 설정
        .addConverterFactory(GsonConverterFactory.create()) // 응답을 Gson을 사용하여 파싱
        .client(createOkHttpClient())                 // 위에서 생성한 OkHttpClient 인스턴스를 설정
        .build()

    // Retrofit 인스턴스와 인터페이스를 연결하여 API 요청을 준비합니다.
    val imageNetWork: NetWorkInterface = imageRetrofit.create(NetWorkInterface::class.java)
}