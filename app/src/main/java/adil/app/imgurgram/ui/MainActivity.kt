package adil.app.imgurgram.ui

import adil.app.imgurgram.R
import adil.app.imgurgram.databinding.ActivityMainBinding
import adil.app.imgurgram.ui.stories.StoriesRecyclerAdapter
import adil.app.imgurgram.ui.stories.StoriesViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storiesViewModel: StoriesViewModel by viewModels()
    private val storiesAdapter = StoriesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNav()
        binding.storiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = storiesAdapter
        }
        storiesViewModel.fetchTags()
    }

    private fun setupNav() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        storiesViewModel.tags.observe(this) {
            storiesAdapter.submitList(it)
        }
    }
}