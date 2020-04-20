package money.nala.pay.interview

import android.app.Application
import android.content.Context
import androidx.annotation.Nullable
import money.nala.pay.interview.di.injectFeature
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import timber.log.Timber

open class NalaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        initTimber()
        initKOIN()
        injectFeature()
    }

    companion object {
        lateinit var appContext: Context
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                @Nullable
                override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }

    private fun initKOIN() {
        try {
            startKoin {
                androidLogger()
                androidContext(applicationContext)
            }
        } catch (error: KoinAppAlreadyStartedException) {
            Timber.e(error.localizedMessage)
        }
    }
}