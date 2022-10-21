package adil.app.imgurgram.ui.stories

import adil.app.imgurgram.data.ImgurRepository
import adil.app.libimgur.models.Gallery
import adil.app.libimgur.models.Tag
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class StoriesViewModel: ViewModel() {

    private val repo = ImgurRepository()
    private val _tags = MutableLiveData<List<Tag>>()

    val tags: LiveData<List<Tag>> = _tags

    fun fetchTags() = viewModelScope.launch(Dispatchers.IO) {
        _tags.postValue(repo.getTags())
    }

}