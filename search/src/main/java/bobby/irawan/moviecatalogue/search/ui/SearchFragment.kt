package bobby.irawan.moviecatalogue.search.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_TV_SHOW
import bobby.irawan.moviecatalogue.search.R
import bobby.irawan.moviecatalogue.search.databinding.FragmentSearchBinding
import bobby.irawan.moviecatalogue.search.di.searchModule
import bobby.irawan.moviecatalogue.utils.DataMapper
import bobby.irawan.moviecatalogue.utils.showToast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private val viewModel by viewModel<SearchViewModel>()

    init {
        loadKoinModules(searchModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding?.radioGroupSearchType?.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.radio_button_movie -> {
                    viewModel.setSearchType(ITEM_MOVIE)
                }
                R.id.radio_button_tv_show -> {
                    viewModel.setSearchType(ITEM_TV_SHOW)
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.movie.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val movies = items.map { DataMapper.movieDomainToPresentation(it) }
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                }
            )
        }
        viewModel.tvShow.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val movies = items.map { DataMapper.tvShowDomainToPresentation(it) }
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            isIconified = false
            requestFocus()
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryHint = resources.getString(R.string.search_hint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.searchKeyword(text.toString())
                    }
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    return false
                }
            })

            val searchViewLayoutParent = (this.getChildAt(0) as LinearLayout)
            val searchEditFrame: LinearLayout = searchViewLayoutParent.getChildAt(2) as LinearLayout
            val layoutParams: LinearLayout.LayoutParams =
                searchEditFrame.layoutParams as LinearLayout.LayoutParams
            layoutParams.leftMargin = -16
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

}