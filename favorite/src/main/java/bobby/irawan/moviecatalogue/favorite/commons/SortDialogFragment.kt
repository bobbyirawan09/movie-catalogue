package bobby.irawan.moviecatalogue.favorite.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.FragmentSortDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortDialogFragment : BottomSheetDialogFragment() {

    private var currentSort: Int = 1
    private var binding: FragmentSortDialogBinding? = null
    private var sortListener: SortListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupListener()
    }

    private fun setupView() {
        when (currentSort) {
            1 -> {
                binding?.textViewAscending?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                binding?.textViewAscending?.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_done),
                    null
                )
            }
            2 -> {
                binding?.textViewDescending?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                binding?.textViewDescending?.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_done),
                    null
                )
            }
            3 -> {
                binding?.textViewHighestVoteAverage?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                binding?.textViewHighestVoteAverage?.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_done),
                    null
                )
            }
            4 -> {
                binding?.textViewLowestVoteAverage?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                binding?.textViewLowestVoteAverage?.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_done),
                    null
                )
            }
        }
    }

    private fun setupListener() {
        binding?.textViewAscending?.setOnClickListener {
            onSortSelected(1)
        }
        binding?.textViewDescending?.setOnClickListener {
            onSortSelected(2)
        }
        binding?.textViewHighestVoteAverage?.setOnClickListener {
            onSortSelected(3)
        }
        binding?.textViewLowestVoteAverage?.setOnClickListener {
            onSortSelected(4)
        }
    }

    private fun onSortSelected(choice: Int) {
        sortListener?.onSortSelected(choice)
        dismiss()
    }

    companion object {
        private const val TAG = "SortDialogFragment"
        fun show(
            fragmentManager: FragmentManager,
            currentSort: Int = 1,
            sortListener: SortListener
        ) {
            SortDialogFragment().apply {
                this.currentSort = currentSort
                this.sortListener = sortListener
                isCancelable = true
                show(fragmentManager, TAG)
            }
        }
    }

    interface SortListener {
        fun onSortSelected(choice: Int)
    }

}