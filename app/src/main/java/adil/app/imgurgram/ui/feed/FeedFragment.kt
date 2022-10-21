package adil.app.imgurgram.ui.feed

import adil.app.imgurgram.databinding.FragmentFeedBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

@Suppress("DEPRECATION")
class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private val viewModel: FeedViewModel by viewModels()
    private val feedAdapter = FeedRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val feed = arguments?.getString("feed")
        feed?.let { viewModel.updateFeed(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRecyclerView.adapter = feedAdapter

        viewModel.feed.observe({lifecycle}) {
            feedAdapter.submitList(it)
        }
        return binding.root
    }

}