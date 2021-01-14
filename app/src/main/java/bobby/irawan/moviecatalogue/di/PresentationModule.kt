package bobby.irawan.moviecatalogue.di

import bobby.irawan.moviecatalogue.presentation.detail.movie.MovieDetailViewModel
import bobby.irawan.moviecatalogue.presentation.detail.tvshow.TvShowDetailViewModel
import bobby.irawan.moviecatalogue.presentation.home.movie.MovieViewModel
import bobby.irawan.moviecatalogue.presentation.home.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Bobby Irawan on 25/10/20.
 */

val presentationModule = module {
    viewModel {
        MovieViewModel(get())
    }

    viewModel {
        TvShowViewModel(get())
    }

    viewModel {
        TvShowDetailViewModel(get())
    }

    viewModel {
        MovieDetailViewModel(get())
    }
}