package bobby.irawan.moviecatalogue.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Bobby Irawan on 28/10/20.
 */
class ViewPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifecycle) {

    val title = mutableListOf<String>()

    private val fragments = mutableListOf<Fragment>()

    fun setTitle(vararg titles: String) {
        title.addAll(titles.asList())
    }

    fun setFragment(vararg fragment: Fragment) {
        fragments.addAll(fragment.asList())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}