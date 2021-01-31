package bobby.irawan.moviecatalogue.favorite.ui.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.FragmentTvShowFavoriteBinding
import bobby.irawan.moviecatalogue.favorite.ui.adapter.ItemFavoriteAdapter
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment(R.layout.fragment_tv_show_favorite) {

    private val binding: FragmentTvShowFavoriteBinding by viewBinding()
    private val viewModel by viewModel<TvShowFavoriteViewModel>()
    private val adapter by lazy {
        ItemFavoriteAdapter {
            TvShowDetailActivity.startActivity(requireContext(), it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding.recyclerViewItem.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getTvShowFavorites().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}