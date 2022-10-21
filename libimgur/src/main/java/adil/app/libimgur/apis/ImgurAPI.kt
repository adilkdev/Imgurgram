package adil.app.libimgur.apis

import adil.app.libimgur.models.GalleryResponse
import adil.app.libimgur.models.TagResponse
import adil.app.libimgur.models.TagsResponse
import adil.app.libimgur.params.Section
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/{section}")
    suspend fun getGallery(
        @Path("section") section: Section,
        @Query("album_previews") albumPreviews: Boolean? = true
    ) : Response<GalleryResponse>

    @GET("tags")
    suspend fun getTags() : Response<TagsResponse>

    @GET("gallery/t/{tag}")
    suspend fun getTagGallery(
        @Path("tag") tag: String
    ): Response<TagResponse>

}