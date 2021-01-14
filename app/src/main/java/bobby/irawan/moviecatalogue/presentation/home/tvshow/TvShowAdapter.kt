package bobby.irawan.moviecatalogue.presentation.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.ItemMovieBinding
import bobby.irawan.moviecatalogue.presentation.model.TvShowModelView
import bobby.irawan.moviecatalogue.utils.orNoInfoString
import bobby.irawan.moviecatalogue.utils.setForMovieFavorite

/**
 * Created by Bobby Irawan on 30/10/20.
 */
class TvShowAdapter(private val listener: TvShowAdapterListener?) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<TvShowModelView>() {
        override fun areItemsTheSame(
            oldItem: TvShowModelView,
            newItem: TvShowModelView
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TvShowModelView,
            newItem: TvShowModelView
        ): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<TvShowModelView>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowItem: TvShowModelView) {
            with(binding) {
                textViewTitle.text = tvShowItem.title.orNoInfoString()
                imageViewBanner.setForMovieFavorite(tvShowItem.posterUrl)
                textViewReleaseDate.text = tvShowItem.firstAirDate.orNoInfoString()
                textViewSynopsis.text = tvShowItem.overview.orNoInfoString()
                layoutVoteAverage.textViewVoteAverage.text = tvShowItem.voteAverage.toString()
                textViewTotalVotes.text =
                    root.context.getString(
                        R.string.vote_count_label,
                        tvShowItem.voteCount.toString()
                    )
                root.setOnClickListener { listener?.onClickTvShow(tvShowItem.id) }
            }
        }
    }

    interface TvShowAdapterListener {
        fun onClickTvShow(tvShowId: Int)
        fun onLoadNextPage()
    }
}