package bobby.irawan.moviecatalogue.presentation.detail.movie

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.observe
import bobby.irawan.moviecatalogue.R
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.databinding.ActivityMovieDetailBinding
import bobby.irawan.moviecatalogue.utils.DataMapper
import bobby.irawan.moviecatalogue.utils.setForMovieBanner
import bobby.irawan.moviecatalogue.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel by viewModel<MovieDetailViewModel>()
    private lateinit var title: String
    private lateinit var voteAverage: String
    private lateinit var imdbId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupListener()
        observeViewModel()

        viewModel.getFromBundle(intent)
        viewModel.checkIsFavorite(viewModel.movieId)
        viewModel.getMovieDetail()
    }

    private fun setupListener() {
        with(binding) {
            imageViewBack.setOnClickListener {
                onBackPressed()
            }
            imageViewShare.setOnClickListener {
                val message =
                    applicationContext.getString(R.string.share_message, title, voteAverage)
                ShareCompat.IntentBuilder.from(this@MovieDetailActivity)
                    .setType("text/plain")
                    .setChooserTitle(getString(R.string.share_title))
                    .setText(message)
                    .startChooser()
            }
            layoutBottomSheet.buttonToImdb.setOnClickListener {
                val url = applicationContext.getString(R.string.imdb_link, imdbId)
                val query: String = Uri.encode(url, "UTF-8")
                val browserIntent =
                    Intent(CATEGORY_BROWSABLE, Uri.parse(Uri.decode(query)))
                browserIntent.action = ACTION_VIEW
                startActivity(browserIntent)
            }
            imageViewFavorite.setOnClickListener {
                viewModel.onFavoriteClick()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.movieDetail.observe(this) {
            it.handleResult(
                successDataBlock = { item ->
                    val movieDetail = DataMapper.movieDetailDomainToPresentation(item)
                    viewModel.setItemDetailModelView(movieDetail)
                    with(binding) {
                        title = movieDetail.title
                        voteAverage = movieDetail.voteAverage.toString()
                        imdbId = movieDetail.imdbId

                        imageViewBackdrop.setForMovieBanner(movieDetail.posterUrl)
                        textViewMovieTitle.text = movieDetail.title
                        viewModel.generateMovieGenre(movieDetail.genres)

                        with(layoutBottomSheet) {
                            textViewVoteAverage.text = movieDetail.voteAverage.toString()
                            textViewVoteCount.text = applicationContext.getString(
                                R.string.vote_count_label,
                                movieDetail.voteCount.toString()
                            )
                            textViewMinute.text = movieDetail.duration.toString()
                            textViewOverview.text = movieDetail.overview
                            textViewReleaseYear.text = movieDetail.releaseDate
                        }
                    }
                },
                errorBlock = {
                    showToast(it?.message.orEmpty())
                }
            )
        }
        viewModel.movieGenre.observe(this) { genre ->
            binding.textViewMovieGenre.text = genre
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
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun startActivity(context: Context, movieId: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
            context.startActivity(intent)
        }
    }
}