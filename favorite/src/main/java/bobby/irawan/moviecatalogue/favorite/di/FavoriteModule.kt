package bobby.irawan.moviecatalogue.favorite.di

import bobby.irawan.moviecatalogue.favorite.ui.movie.MovieFavoriteViewModel
import bobby.irawan.moviecatalogue.favorite.ui.tvshow.TvShowFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Bobby Irawan on 01/01/21.
 */
val favoriteModule = module {
    viewModel {
        MovieFavoriteViewModel(get())
    }

    viewModel {
        TvShowFavoriteViewModel(get())
    }
}