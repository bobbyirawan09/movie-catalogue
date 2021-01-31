package bobby.irawan.moviecatalogue.search.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_TV_SHOW
import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailActivity
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailActivity
import bobby.irawan.moviecatalogue.search.R
import bobby.irawan.moviecatalogue.search.databinding.FragmentSearchBinding
import bobby.irawan.moviecatalogue.search.di.searchModule
import bobby.irawan.moviecatalogue.search.ui.adapter.SearchItemAdapter
import bobby.irawan.moviecatalogue.search.utils.DataMapper
import bobby.irawan.moviecatalogue.utils.*
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment(R.layout.fragment_search), SearchItemAdapter.SearchAdapterListener {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = SearchItemAdapter(this)

    init {
        loadKoinModules(searchModule)
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
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.recyclerViewTvShow.adapter = adapter
    }

    private fun initListener() {
        binding.radioGroupSearchType.setOnCheckedChangeListener { radioGroup, i ->
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
        viewModel.searchResult.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val tvShows = items.map { DataMapper.searchDomainToPresentation(it) }
                    viewModel.searchItem.addAll(tvShows)
                    adapter.submitList(viewModel.searchItem)
                    binding.textViewEmptyDataMessage.isShowEmptyInfo(viewModel.searchItem)
                    binding.shimmer.setGoneAndStop()
                    binding.recyclerViewTvShow.orGone(viewModel.searchItem)
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                    binding.shimmer.setGoneAndStop()
                    binding.textViewEmptyDataMessage.showNoInfoIf(viewModel.searchItem)
                }
            ) { state ->
                when (state) {
                    is Result.State.NoInternet -> {
                        binding.root.showNoInternetSnackbar { viewModel.retryConnection() }
                        binding.shimmer.setGoneAndStop()
                    }
                    else -> {
                        //Do nothing
                    }
                }
            }
        }
        viewModel.loading().observe(viewLifecycleOwner) {
            binding.linearLayoutProgressBottom.showSlidingIf(it)
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
                override fun onQueryTextSubmit(text: String): Boolean {
                    lifecycleScope.launch {
                        adapter.submitList(null)
                        binding.recyclerViewTvShow.setGone()
                        binding.textViewEmptyDataMessage.setGone()
                        binding.shimmer.setVisibleAndStart()
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

    override fun onClickItem(itemId: Int) {
        if (viewModel.searchType.equals(ITEM_MOVIE)) {
            MovieDetailActivity.startActivity(requireActivity(), itemId)
        } else {
            TvShowDetailActivity.startActivity(requireContext(), itemId)
        }
    }

    override fun onLoadNextPage() {
        viewModel.searchKeywordNextPage()
    }

}