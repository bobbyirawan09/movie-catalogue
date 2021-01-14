package bobby.irawan.moviecatalogue.favorite.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.databinding.ItemMovieBinding
import bobby.irawan.moviecatalogue.favorite.R
import bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView
import bobby.irawan.moviecatalogue.utils.orNoInfoString
import bobby.irawan.moviecatalogue.utils.setForMovieFavorite

/**
 * Created by Bobby Irawan on 23/11/20.
 */
class ItemFavoriteAdapter(private val onClick: (itemId: Int) -> Unit) :
    RecyclerView.Adapter<ItemFavoriteAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<FavoriteModelView>() {
        override fun areItemsTheSame(
            oldItem: FavoriteModelView,
            newItem: FavoriteModelView
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: FavoriteModelView,
            newItem: FavoriteModelView
        ): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<FavoriteModelView>) = differ.submitList(list)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteDomain: FavoriteModelView) {
            with(binding) {
                textViewTitle.text = favoriteDomain.title.orNoInfoString()
                imageViewBanner.setForMovieFavorite(favoriteDomain.posterUrl)
                textViewReleaseDate.text = favoriteDomain.releaseDate.orNoInfoString()
                textViewSynopsis.text = favoriteDomain.overview.orNoInfoString()
                layoutVoteAverage.textViewVoteAverage.text = favoriteDomain.voteAverage.toString()
                textViewTotalVotes.text =
                    root.context.getString(
                        R.string.vote_count_label,
                        favoriteDomain.voteCount.toString()
                    )
            }
            itemView.setOnClickListener {
                onClick(favoriteDomain.id)
            }
        }
    }
}