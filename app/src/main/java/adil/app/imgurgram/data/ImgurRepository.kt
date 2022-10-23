package adil.app.imgurgram.data

import adil.app.libimgur.ImgurClient
import adil.app.libimgur.models.Gallery
import adil.app.libimgur.models.Image
import adil.app.libimgur.models.Tag
import adil.app.libimgur.params.Section

class ImgurRepository {

    private val api = ImgurClient.api

    suspend fun getHotFeed(): List<Image>? { // TODO: return a propert error object if null
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTopFeed(): List<Image>? {
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTags(): List<Tag>? {
        val response = api.getTags()
        return response.body()?.data?.tags
    }

    suspend fun getTagGallery(tagName: String) : List<Image>? {
        val response = api.getTagGallery(tagName)
        return response.body()?.data?.items
    }

}