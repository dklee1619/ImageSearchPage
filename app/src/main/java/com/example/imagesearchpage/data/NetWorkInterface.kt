package com.example.imagesearchpage.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterface {
    // @GET : 서버 연동 시 GET 방식으로 하겠다는 의미
    // @Query : 서버에 전달되는 데이터
    // @Url : 요청 URL
    @GET("/v2/search/image") // https://dapi.kakao.com/v2/search/image 중 https://dapi.kakao.com/는 BASE_URL이고 나머지 v2/search/image
    suspend fun searchImages(
        @QueryMap param: HashMap<String, String>
    ): Response  // 이 메서드는 `Response` 타입의 응답을 반환합니다.
}
/*
[서비스 인터페이스 정의]
4. GET에는, NetWorkClient의 BASE_URL 뒤에붙여줄 주소를 적음. 그 주소로 GET 요청을 한다는거
4. 모르겠다~~~ Retrofit은 GPT를통해 해결 이건 GPT한테 도움받고 코드분석할수밖에없는듯!!!!
 */
