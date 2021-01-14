package bobby.irawan.moviecatalogue.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bobby.irawan.moviecatalogue.databinding.FragmentHomeBinding
import bobby.irawan.moviecatalogue.presentation.MainActivity
import bobby.irawan.moviecatalogue.presentation.common.ViewPagerAdapter
import bobby.irawan.moviecatalogue.presentation.home.movie.MovieFragment
import bobby.irawan.moviecatalogue.presentation.home.tvshow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter =
            ViewPagerAdapter(this)
        pagerAdapter.setFragment(MovieFragment(), TvShowFragment())

        setupViewPager()
    }

    private fun setupViewPager() {
        binding?.let {
            with(it) {
                viewPagerHome.adapter = pagerAdapter
                TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, pos ->
                    tab.text = pagerAdapter.title[pos]
                }.attach()
            }
        }
    }
}