package adil.app.imgurgram.ui.feed

import adil.app.imgurgram.data.ImgurRepository
import adil.app.libimgur.models.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val repo = ImgurRepository()
    private val _feed = MutableLiveData<List<Image>>()
    val feed: LiveData<List<Image>> = _feed

    fun updateFeed(feedType: String) {
        viewModelScope.launch {
            when (feedType) {
                "hot" -> _feed.postValue(repo.getHotFeed())
                "top" -> _feed.postValue(repo.getTopFeed())
                else -> Log.e("FEED", "Feed has to be either HOT or TOP")
            }
        }
    }

}