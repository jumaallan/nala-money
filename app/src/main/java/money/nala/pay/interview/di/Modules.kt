package money.nala.pay.interview.di

import money.nala.pay.interview.data.repository.TransactionRepository
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {

    loadKoinModules(
            listOf(
                    repositoriesModule,
                    viewModelsModule
            )
    )
}

val repositoriesModule = module {
    single { TransactionRepository() }
}

val viewModelsModule = module {
    viewModel { TransactionViewModel(get()) }
}