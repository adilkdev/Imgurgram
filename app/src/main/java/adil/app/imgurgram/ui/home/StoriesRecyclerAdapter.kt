package adil.app.imgurgram.ui.home

import adil.app.imgurgram.databinding.ListItemStoryHeadBinding
import adil.app.imgurgram.ui.story.StoryActivity
import adil.app.libimgur.models.Tag
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class StoriesRecyclerAdapter :
    ListAdapter<Tag, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemStoryHeadBinding.inflate(inflater, parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StoriesDiffCallback : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag) = oldItem == newItem
    }

    class StoriesViewHolder(binding: ListItemStoryHeadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val storyHeadImageView = binding.storyHeadImageView
        private val storyHeadTextView = binding.storyHeadTextView
        private val root = binding.root
        fun bind(tag: Tag) {
            storyHeadTextView.text = tag.displayName
            storyHeadImageView.load("https://i.imgur.com/${tag.backgroundHash}.jpg")
            root.apply {
                setOnClickListener {
                    context.startActivity(
                        Intent(context, StoryActivity::class.java)
                            .putExtra(StoryActivity.TAG, tag.name)
                    )
                }
            }
        }
    }

}