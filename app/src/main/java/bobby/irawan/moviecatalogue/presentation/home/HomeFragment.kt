package bobby.irawan.moviecatalogue.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.FragmentHomeBinding
import bobby.irawan.moviecatalogue.presentation.common.ViewPagerAdapter
import bobby.irawan.moviecatalogue.presentation.home.movie.MovieFragment
import bobby.irawan.moviecatalogue.presentation.home.tvshow.TvShowFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter =
            ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        pagerAdapter.setTitle(getString(R.string.movies_title), getString(R.string.tv_shows_title))
        pagerAdapter.setFragment(MovieFragment(), TvShowFragment())

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.let {
            with(it) {
                viewPagerHome.adapter = pagerAdapter
                TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, pos ->
                    tab.text = pagerAdapter.title[pos]
                }.attach()
            }
        }
    }
}