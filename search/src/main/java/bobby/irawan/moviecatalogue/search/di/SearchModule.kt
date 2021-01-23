package bobby.irawan.moviecatalogue.search.di

import bobby.irawan.moviecatalogue.search.ui.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Bobby Irawan on 22/01/21.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@FlowPreview
val searchModule = module {
    viewModel {
        SearchViewModel(get())
    }
}