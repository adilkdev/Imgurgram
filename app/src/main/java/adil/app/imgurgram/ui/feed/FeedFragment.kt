package adil.app.imgurgram.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import adil.app.imgurgram.R
import android.widget.TextView

@Suppress("DEPRECATION")
class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val feed = arguments?.getString("feed")
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)
        feed?.let { feedType ->
            rootView.findViewById<TextView>(R.id.tvFeedType).text = feedType
        }
        return rootView
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
    }

}