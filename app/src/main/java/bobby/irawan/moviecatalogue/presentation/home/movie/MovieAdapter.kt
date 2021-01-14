package bobby.irawan.moviecatalogue.presentation.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.databinding.ItemMovieBinding
import bobby.irawan.moviecatalogue.presentation.model.MovieModelView
import bobby.irawan.moviecatalogue.utils.orNoInfoString
import bobby.irawan.moviecatalogue.utils.setForMovieFavorite

/**
 * Created by Bobby Irawan on 30/10/20.
 */
class MovieAdapter(private val listener: MoviesAdapterListener?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MovieModelView>() {
        override fun areItemsTheSame(
            oldItem: MovieModelView,
            newItem: MovieModelView
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieModelView,
            newItem: MovieModelView
        ): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<MovieModelView>) = differ.submitList(list)

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
        fun bind(movieItem: MovieModelView) {
            with(binding) {
                textViewTitle.text = movieItem.title.orNoInfoString()
                imageViewBanner.setForMovieFavorite(movieItem.posterUrl)
                textViewReleaseDate.text = movieItem.releaseDate.orNoInfoString()
                textViewSynopsis.text = movieItem.overview.orNoInfoString()
                layoutVoteAverage.textViewVoteAverage.text = movieItem.voteAverage.toString()
                textViewTotalVotes.text =
                    root.context.getString(
                        R.string.vote_count_label,
                        movieItem.voteCount.toString()
                    )
                root.setOnClickListener { listener?.onClickMovie(movieItem.id) }
            }
        }
    }

    interface MoviesAdapterListener {
        fun onClickMovie(movieId: Int)
        fun onLoadNextPage()
    }
}