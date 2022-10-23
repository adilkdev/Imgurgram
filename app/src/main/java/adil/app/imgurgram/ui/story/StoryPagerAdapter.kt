package adil.app.imgurgram.ui.story

import adil.app.imgurgram.databinding.PageItemStoryBinding
import adil.app.libimgur.models.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import coil.request.ImageRequest

class StoryPagerAdapter :
    ListAdapter<Image, StoryPagerAdapter.StoryPageViewHolder>(StoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PageItemStoryBinding.inflate(inflater, parent, false)
        return StoryPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryPageViewHolder, position: Int) {
        val image = getItem(position)
        val imageUrl = if (image?.isAlbum == true && image.imagesCount != 0) {
            image.images?.get(0)?.link
        } else {
            image.link
        }
        imageUrl?.let {
            holder.storyImageView.load(it)
            holder.storyLinkTextView.text = it
        }
        cacheNext(position, holder.storyImageView)
    }

    private fun cacheNext(position: Int, imageView: ImageView) {
        val image = try {
            getItem(position + 1)
        } catch (e: Exception) {
            null
        }
        val imageUrl = if (image?.isAlbum == true && image.imagesCount != 0) {
            image.images?.get(0)?.link
        } else {
            image?.link
        }
        imageUrl?.let {
            val request = ImageRequest.Builder(imageView.context)
                .data(it)
                .build()
            Coil.imageLoader(imageView.context).enqueue(request)
        }
    }

    class StoryDiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
    }

    class StoryPageViewHolder(binding: PageItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val storyImageView = binding.storyImageView
        val storyLinkTextView = binding.storyLinkTextView
    }

}