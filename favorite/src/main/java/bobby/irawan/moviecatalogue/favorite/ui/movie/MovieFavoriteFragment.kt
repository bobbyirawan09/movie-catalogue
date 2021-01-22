package bobby.irawan.moviecatalogue.favorite.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import bobby.irawan.moviecatalogue.favorite.ui.adapter.ItemFavoriteAdapter
import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailActivity
import bobby.irawan.moviecatalogue.utils.CountingIdlingResourceSingleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private var binding: FragmentMovieFavoriteBinding? = null
    private val viewModel by viewModel<MovieFavoriteViewModel>()
    private val adapter by lazy {
        ItemFavoriteAdapter {
            MovieDetailActivity.startActivity(requireContext(), it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding?.recyclerViewItem?.adapter = adapter
    }

    private fun setupObserver() {
        CountingIdlingResourceSingleton.increment()
        viewModel.getMovieFavorites().observe(viewLifecycleOwner) {
            CountingIdlingResourceSingleton.decrement()
            adapter.submitList(it)
        }
    }
}