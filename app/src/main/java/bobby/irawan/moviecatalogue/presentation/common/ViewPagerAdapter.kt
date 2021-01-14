package bobby.irawan.moviecatalogue.presentation.common

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.presentation.home.movie.MovieFragment
import bobby.irawan.moviecatalogue.presentation.home.tvshow.TvShowFragment

/**
 * Created by Bobby Irawan on 28/10/20.
 */
class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    val title = listOf(
        fragment.context?.getString(R.string.movies_title).orEmpty(),
        fragment.context?.getString(R.string.tv_shows_title).orEmpty()
    )

    private val fragments = mutableListOf<Fragment>()

    fun setFragment(vararg fragment: Fragment) {
        fragments.addAll(fragment.asList())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}