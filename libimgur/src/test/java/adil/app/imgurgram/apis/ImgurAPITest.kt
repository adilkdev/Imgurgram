package adil.app.imgurgram.apis

import adil.app.libimgur.ImgurClient
import adil.app.libimgur.apis.ImgurAPI
import adil.app.libimgur.params.Section
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurAPITest {

    private val api = ImgurClient.api

    @Test
    fun `get tags is working`() = runBlocking {
        val response = api.getTags()
        assertNotNull(response.body())
    }

    @Test
    fun `get tag - aww working`() = runBlocking {
        val response = api.getTagGallery("aww")
        assertNotNull(response.body())
    }

    @Test
    fun `get gallery - hot working`() = runBlocking {
        val response = api.getGallery(Section.HOT)
        assertNotNull(response.body())
    }

    @Test
    fun `get gallery - top working`() = runBlocking {
        val response = api.getGallery(Section.TOP)
        assertNotNull(response.body())
    }

}