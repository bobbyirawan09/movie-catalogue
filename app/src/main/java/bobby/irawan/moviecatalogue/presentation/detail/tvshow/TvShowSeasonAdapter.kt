package bobby.irawan.moviecatalogue.presentation.detail.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.databinding.ItemSeasonBinding
import bobby.irawan.moviecatalogue.presentation.model.SeasonModelView
import bobby.irawan.moviecatalogue.utils.setForMovieInfo

/**
 * Created by Bobby Irawan on 01/11/20.
 */
class TvShowSeasonAdapter :
    RecyclerView.Adapter<TvShowSeasonAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<SeasonModelView>() {
        override fun areItemsTheSame(
            oldItem: SeasonModelView,
            newItem: SeasonModelView
        ): Boolean {
            return oldItem.posterUrl == newItem.posterUrl
        }

        override fun areContentsTheSame(
            oldItem: SeasonModelView,
            newItem: SeasonModelView
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(data: List<SeasonModelView>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(season: SeasonModelView) {
            with(binding) {
                imageViewBanner.setForMovieInfo(season.posterUrl)
                textViewReleaseDate.text = season.airDate
                textViewTitle.text = season.title
            }
        }
    }
}
