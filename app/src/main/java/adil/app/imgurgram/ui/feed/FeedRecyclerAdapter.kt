package adil.app.imgurgram.ui.feed

import adil.app.imgurgram.R
import adil.app.imgurgram.databinding.ListItemHomeBinding
import adil.app.libimgur.models.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class FeedRecyclerAdapter :
    ListAdapter<Image, FeedRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHomeBinding.inflate(inflater, parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val image = getItem(position)
        holder.captionTextView.text = image.title
        val url = if (!image.cover.isNullOrBlank()) "https://i.imgur.com/${image.cover}.jpg"
        else image.link
        holder.imageView.load(url) {
            placeholder(R.drawable.placeholder_image)
            error(R.drawable.placeholder_image)
        }
    }

    private class FeedDiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

    }

    class FeedViewHolder(binding: ListItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val captionTextView = binding.captionTextView
        val imageView = binding.imageView
    }
}