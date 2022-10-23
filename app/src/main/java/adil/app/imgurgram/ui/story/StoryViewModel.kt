package adil.app.imgurgram.ui.story

import adil.app.imgurgram.data.ImgurRepository
import adil.app.libimgur.models.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StoryViewModel: ViewModel() {

    private val repo = ImgurRepository()
    private val _images = MutableLiveData<List<Image>>()

    val images: LiveData<List<Image>> = _images

    fun fetchTags(tagName: String) = viewModelScope.launch(IO) {
        _images.postValue(repo.getTagGallery(tagName))
    }
}