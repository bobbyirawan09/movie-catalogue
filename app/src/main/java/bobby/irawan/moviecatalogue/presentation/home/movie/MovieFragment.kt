package bobby.irawan.moviecatalogue.presentation.home.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.databinding.FragmentMovieBinding
import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailActivity
import bobby.irawan.moviecatalogue.utils.*
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.MoviesAdapterListener {

    private val binding: FragmentMovieBinding by viewBinding()
    private val viewModel by viewModel<MovieViewModel>()
    private val adapter = MovieAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModel()
        CountingIdlingResourceSingleton.increment()
        viewModel.loadMovieInitial()
    }

    private fun setUpView() {
        binding.recyclerViewMovie.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movieItems.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val movies = items.map { DataMapper.movieDomainToPresentation(it) }
                    viewModel.movies.addAll(movies)
                    adapter.submitList(viewModel.movies)
                    binding.recyclerViewMovie.orGone(movies)
                    binding.textViewEmptyDataMessage.isShowEmptyInfo(movies)
                    binding.shimmer.setGoneAndStop()
                    CountingIdlingResourceSingleton.decrement()
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                    binding.shimmer.setGoneAndStop()
                    binding.textViewEmptyDataMessage.showNoInfoIf(viewModel.movies)
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

    override fun onClickMovie(movieId: Int) {
        MovieDetailActivity.startActivity(requireActivity(), movieId)
    }

    override fun onLoadNextPage() {
        viewModel.loadMovieNextPage()
    }

    override fun onDestroyView() {
        binding.recyclerViewMovie.adapter = null
        super.onDestroyView()
    }

}