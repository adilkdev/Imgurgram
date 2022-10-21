package adil.app.imgurgram.ui.stories

import adil.app.imgurgram.databinding.ListItemStoryHeadBinding
import adil.app.libimgur.models.Gallery
import adil.app.libimgur.models.Tag
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class StoriesRecyclerAdapter() :
    ListAdapter<Tag, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemStoryHeadBinding.inflate(inflater, parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val tag = getItem(position)
        holder.storyHeadTextView.text = tag.displayName
        holder.storyHeadImageView.load("https://i.imgur.com/${tag.backgroundHash}.jpg")
    }

    private class StoriesDiffCallback : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag) = oldItem == newItem
    }

    class StoriesViewHolder(binding: ListItemStoryHeadBinding) : RecyclerView.ViewHolder(binding.root) {
        val storyHeadImageView = binding.storyHeadImageView
        val storyHeadTextView = binding.storyHeadTextView
    }

}