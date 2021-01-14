package bobby.irawan.moviecatalogue.presentation.detail.tvshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.databinding.ActivityTvShowDetailBinding
import bobby.irawan.moviecatalogue.utils.DataMapper
import bobby.irawan.moviecatalogue.utils.setForMovieBanner
import bobby.irawan.moviecatalogue.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private val viewModel by viewModel<TvShowDetailViewModel>()
    private lateinit var title: String
    private lateinit var voteAverage: String
    private val adapter =
        TvShowSeasonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setUpView()
        setupListener()
        observeViewModel()

        viewModel.getFromBundle(intent)
        viewModel.checkIsFavorite(viewModel.tvShowId)
        viewModel.getTvShowDetail()
    }

    private fun setUpView() {
        binding.layoutBottomSheet.recyclerViewSeason.adapter = adapter
    }

    private fun setupListener() {
        with(binding) {
            imageViewBack.setOnClickListener {
                onBackPressed()
            }
            imageViewShare.setOnClickListener {
                val message =
                    applicationContext.getString(R.string.share_message, title, voteAverage)
                ShareCompat.IntentBuilder.from(this@TvShowDetailActivity)
                    .setType("text/plain")
                    .setChooserTitle(getString(R.string.share_title))
                    .setText(message)
                    .startChooser()
            }
            imageViewFavorite.setOnClickListener {
                viewModel.onFavoriteClick()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.tvShowDetail.observe(this) {
            it.handleResult(
                successDataBlock = { item ->
                    val tvShowDetail = DataMapper.tvShowDetailDomainToPresentation(item)
                    viewModel.setItemDetailModelView(tvShowDetail)
                    with(binding) {
                        title = tvShowDetail.title
                        voteAverage = tvShowDetail.voteAverage.toString()

                        imageViewBackdrop.setForMovieBanner(tvShowDetail.posterUrl)
                        textViewTvShowTitle.text = tvShowDetail.title
                        viewModel.generateTvShowGenre(tvShowDetail.genres)
                        adapter.submitList(tvShowDetail.seasons)

                        with(layoutBottomSheet) {
                            textViewVoteAverage.text = tvShowDetail.voteAverage.toString()
                            textViewVoteCount.text = applicationContext.getString(
                                R.string.vote_count_label,
                                tvShowDetail.voteCount.toString()
                            )
                            textViewEpisodes.text = tvShowDetail.numberOfEpisodes.toString()
                            textViewOverview.text = tvShowDetail.overview
                            textViewReleaseYear.text = tvShowDetail.releaseDate
                        }
                    }
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                }
            )
        }
        viewModel.tvShowGenre.observe(this) { genre ->
            binding.textViewTvShowGenre.text = genre
        }
        viewModel.isFavorite.observe(this) { isFavorite ->
            binding.imageViewFavorite.setImageResource(
                if (isFavorite) {
                    R.drawable.ic_favorited
                } else {
                    R.drawable.ic_favorite_border
                }
            )
        }
    }

    companion object {
        const val EXTRA_TV_SHOW_ID = "EXTRA_TV_SHOW_ID"
        fun startActivity(context: Context, movieId: Int) {
            val intent = Intent(context, TvShowDetailActivity::class.java).apply {
                putExtra(EXTRA_TV_SHOW_ID, movieId)
            }
            context.startActivity(intent)
        }
    }
}