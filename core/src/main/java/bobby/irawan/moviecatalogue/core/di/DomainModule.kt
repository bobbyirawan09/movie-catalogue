package bobby.irawan.moviecatalogue.di

import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCaseImpl
import org.koin.dsl.module

/**
 * Created by Bobby Irawan on 25/10/20.
 */

val domainModule = module {
    single<MovieCatalogueUseCase> { MovieCatalogueUseCaseImpl(get()) }
}