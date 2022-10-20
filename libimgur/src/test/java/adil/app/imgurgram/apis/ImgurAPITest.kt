package adil.app.imgurgram.apis

import adil.app.libimgur.apis.ImgurAPI
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurAPITest {

    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", "Client-ID 13fe9c78fce466c")
                .build()
            it.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.imgur.com/3/")
        .build()

    private val api = retrofit.create(ImgurAPI::class.java)

    @Test
    fun `get tags is working`() {
        val response = api.getTags().execute()
        assertNotNull(response.body())
    }

}