package bobby.irawan.moviecatalogue.favorite.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.databinding.FragmentTvShowFavoriteBinding
import bobby.irawan.moviecatalogue.favorite.commons.SortDialogFragment
import bobby.irawan.moviecatalogue.favorite.ui.adapter.ItemFavoriteAdapter
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment(), SortDialogFragment.SortListener {

    private var binding: FragmentTvShowFavoriteBinding? = null
    private val viewModel by viewModel<TvShowFavoriteViewModel>()
    private val adapter by lazy {
        ItemFavoriteAdapter {
            TvShowDetailActivity.startActivity(requireContext(), it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupView() {
        binding?.recyclerViewItem?.adapter = adapter
    }

    private fun setupListener() {
        binding?.buttonSort?.setOnClickListener {
            SortDialogFragment.show(
                childFragmentManager,
                viewModel.currentSort,
                sortListener = this
            )
        }
    }

    private fun setupObserver() {
        viewModel.getTvShowFavorites().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onSortSelected(choice: Int) {
        viewModel.onSortSelected(choice).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}