package money.nala.pay.interview.di

import android.content.Context
import money.nala.pay.interview.data.settings.Settings
import money.nala.pay.interview.executers.AppExecutors
import money.nala.pay.interview.repository.TransactionRepository
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {

    loadKoinModules(
            listOf(
                    repositoriesModule,
                    viewModelsModule,
                    settingsModule,
                    executorsModule
            )
    )
}

val repositoriesModule = module {
    single { TransactionRepository(get()) }
}

val viewModelsModule = module {
    viewModel { TransactionViewModel(get(), get()) }
}

val settingsModule = module {
    single {
        androidContext().getSharedPreferences(
                "nala_settings",
                Context.MODE_PRIVATE
        )
    }
    single {
        Settings(get())
    }
}

val executorsModule = module {
    single { AppExecutors.getInstance() }
}