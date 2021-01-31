package bobby.irawan.moviecatalogue.presentation.home.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.databinding.FragmentTvShowBinding
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailActivity
import bobby.irawan.moviecatalogue.utils.*
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(R.layout.fragment_tv_show_), TvShowAdapter.TvShowAdapterListener {

    private val binding: FragmentTvShowBinding by viewBinding()
    private val viewModel by viewModel<TvShowViewModel>()
    private val adapter = TvShowAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModel()
        CountingIdlingResourceSingleton.increment()
        viewModel.loadTvShowInitial()
    }

    private fun setUpView() {
        binding.recyclerViewTvShow.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.tvShowItems.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val tvShows = items.map { DataMapper.tvShowDomainToPresentation(it) }
                    viewModel.tvShows.addAll(tvShows)
                    adapter.submitList(viewModel.tvShows)
                    binding.recyclerViewTvShow.orGone(tvShows)
                    binding.textViewEmptyDataMessage.isShowEmptyInfo(tvShows)
                    binding.shimmer.setGoneAndStop()
                    CountingIdlingResourceSingleton.decrement()
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                    binding.shimmer.setGoneAndStop()
                    binding.textViewEmptyDataMessage.showNoInfoIf(viewModel.tvShows)
                }
            ) { state ->
                when (state) {
                    is Result.State.Loading -> {
                        binding.shimmer.startShimmer()
                    }
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

    override fun onClickTvShow(tvShowId: Int) {
        TvShowDetailActivity.startActivity(requireContext(), tvShowId)
    }

    override fun onLoadNextPage() {
        viewModel.loadTvShowNextPage()
    }
}
