package bobby.irawan.moviecatalogue.favorite.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import bobby.irawan.moviecatalogue.databinding.FragmentFavoriteBinding
import bobby.irawan.moviecatalogue.favorite.di.favoriteModule
import bobby.irawan.moviecatalogue.favorite.ui.movie.MovieFavoriteFragment
import bobby.irawan.moviecatalogue.favorite.ui.tvshow.TvShowFavoriteFragment
import bobby.irawan.moviecatalogue.presentation.common.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private lateinit var pagerAdapter: ViewPagerAdapter

    init {
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupView() {
        pagerAdapter =
            ViewPagerAdapter(this)
        pagerAdapter.setFragment(MovieFavoriteFragment(), TvShowFavoriteFragment())
    }

    private fun setupViewPager() {
        binding?.let {
            with(it) {
                viewPagerFavorite.adapter = pagerAdapter
                TabLayoutMediator(tabLayoutFavorite, viewPagerFavorite) { tab, pos ->
                    tab.text = pagerAdapter.title[pos]
                }.attach()
            }
        }
    }

}