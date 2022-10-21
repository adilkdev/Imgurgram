package adil.app.libimgur

import adil.app.libimgur.apis.ImgurAPI
import adil.app.libimgur.converters.EnumConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ImgurClient {
    const val API_KEY = "13fe9c78fce466c" // TODO: ideally should be in app and not lib

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Client-ID $API_KEY")
                    .build()
                it.proceed(request)
            }.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .baseUrl("https://api.imgur.com/3/")
            .build()
    }

    val api: ImgurAPI by lazy {
        retrofit.create(ImgurAPI::class.java)
    }

}