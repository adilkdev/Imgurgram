package adil.app.libimgur.apis

import adil.app.libimgur.models.GalleryResponse
import adil.app.libimgur.models.TagsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ImgurAPI {

    @GET("gallery/hot?album_previews=true")
    fun getGallery() : Call<GalleryResponse>

    @GET("tags")
    fun getTags() : Call<TagsResponse>

}