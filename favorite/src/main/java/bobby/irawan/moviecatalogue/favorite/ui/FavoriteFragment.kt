package bobby.irawan.moviecatalogue.favorite.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.FragmentFavoriteBinding
import bobby.irawan.moviecatalogue.favorite.di.favoriteModule
import bobby.irawan.moviecatalogue.favorite.ui.movie.MovieFavoriteFragment
import bobby.irawan.moviecatalogue.favorite.ui.tvshow.TvShowFavoriteFragment
import bobby.irawan.moviecatalogue.presentation.common.ViewPagerAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private lateinit var pagerAdapter: ViewPagerAdapter

    init {
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
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
            ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        pagerAdapter.setTitle(getString(R.string.movies_title), getString(R.string.tv_shows_title))
        pagerAdapter.setFragment(MovieFavoriteFragment(), TvShowFavoriteFragment())
    }

    private fun setupViewPager() {
        binding.let {
            with(it) {
                viewPagerFavorite.adapter = pagerAdapter
                TabLayoutMediator(tabLayoutFavorite, viewPagerFavorite) { tab, pos ->
                    tab.text = pagerAdapter.title[pos]
                }.attach()
            }
        }
    }

}