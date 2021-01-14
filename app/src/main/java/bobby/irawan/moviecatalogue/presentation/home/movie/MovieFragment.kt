package bobby.irawan.moviecatalogue.presentation.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.databinding.FragmentMovieBinding
import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailActivity
import bobby.irawan.moviecatalogue.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieAdapter.MoviesAdapterListener {

    private var binding: FragmentMovieBinding? = null
    private val viewModel by viewModel<MovieViewModel>()
    private val adapter = MovieAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModel()
        CountingIdlingResourceSingleton.increment()
        viewModel.loadMovieInitial()
    }

    private fun setUpView() {
        binding?.recyclerViewMovie?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movieItems.observe(viewLifecycleOwner) {
            it.handleResult(
                successDataBlock = { items ->
                    val movies = items.map { DataMapper.movieDomainToPresentation(it) }
                    viewModel.movies.addAll(movies)
                    adapter.submitList(viewModel.movies)
                    binding?.recyclerViewMovie?.orGone(movies)
                    binding?.textViewEmptyDataMessage?.isShowEmptyInfo(movies)
                    binding?.shimmer?.setGoneAndStop()
                    CountingIdlingResourceSingleton.decrement()
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                    binding?.shimmer?.setGoneAndStop()
                    binding?.textViewEmptyDataMessage?.showNoInfoIf(viewModel.movies)
                }
            )
        }
        viewModel.loading().observe(viewLifecycleOwner) {
            binding?.linearLayoutProgressBottom?.showSlidingIf(it)
        }
    }

    override fun onClickMovie(movieId: Int) {
        MovieDetailActivity.startActivity(requireActivity(), movieId)
    }

    override fun onLoadNextPage() {
        viewModel.loadMovieNextPage()
    }

}