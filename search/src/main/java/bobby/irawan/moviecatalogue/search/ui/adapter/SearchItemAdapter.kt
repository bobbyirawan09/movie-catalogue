package bobby.irawan.moviecatalogue.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.search.databinding.SearchItemBinding
import bobby.irawan.moviecatalogue.search.model.SearchModelView
import bobby.irawan.moviecatalogue.utils.orNoInfoString
import bobby.irawan.moviecatalogue.utils.setForMovieFavorite

/**
 * Created by Bobby Irawan on 30/10/20.
 */
class SearchItemAdapter(private val listener: SearchAdapterListener?) :
    RecyclerView.Adapter<SearchItemAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SearchModelView>() {
        override fun areItemsTheSame(
            oldItem: SearchModelView,
            newItem: SearchModelView
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SearchModelView,
            newItem: SearchModelView
        ): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SearchModelView>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        if (position == differ.currentList.lastIndex) {
            listener?.onLoadNextPage()
        }
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: SearchModelView) {
            with(binding) {
                textViewTitle.text = movieItem.title.orNoInfoString()
                imageViewBanner.setForMovieFavorite(movieItem.posterUrl)
                textViewReleaseDate.text = movieItem.releaseDate.orNoInfoString()
//                layoutVoteAverage.textViewVoteAverage.text = movieItem.voteAverage.toString()
                textViewTotalVotes.text =
                    root.context.getString(
                        R.string.vote_count_label,
                        movieItem.voteCount.toString()
                    )
                root.setOnClickListener { listener?.onClickItem(movieItem.id) }
            }
        }
    }

    interface SearchAdapterListener {
        fun onClickItem(itemId: Int)
        fun onLoadNextPage()
    }
}