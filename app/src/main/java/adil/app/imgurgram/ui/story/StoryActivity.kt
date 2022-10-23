package adil.app.imgurgram.ui.story

import adil.app.imgurgram.databinding.ActivityStoryBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewPropertyAnimator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class StoryActivity : AppCompatActivity() {

    private val storyViewModel: StoryViewModel by viewModels()
    private lateinit var _binding: ActivityStoryBinding
    private val storyPagerAdapter = StoryPagerAdapter()
    private lateinit var progressAnimator: ViewPropertyAnimator
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val tagName = intent.getStringExtra("tag")
        tagName?.let { it ->
            storyViewModel.fetchTags(it)
        }

        _binding.storyViewPager.apply {
            adapter = storyPagerAdapter
            registerOnPageChangeCallback(pageChangeCallback)
        }
        progressAnimator = _binding.progressView.animate()
    }

    private val nextRunnable = Runnable {
        _binding.storyViewPager.currentItem++
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            // end animation on page change
            _binding.progressView.clearAnimation()
            _binding.progressView.scaleX = 0F
            // start animation for new page
            progressAnimator.scaleX(1F).setStartDelay(10).setDuration(5000L).start()

            handler.removeCallbacks(nextRunnable)
            handler.postDelayed(nextRunnable, 5000)
        }
    }

    override fun onResume() {
        super.onResume()
        storyViewModel.images.observe(this) {
            storyPagerAdapter.submitList(it)
        }
    }
}