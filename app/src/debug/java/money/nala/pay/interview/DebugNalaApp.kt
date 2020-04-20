package money.nala.pay.interview

import com.facebook.stetho.Stetho

class DebugNalaApp : NalaApp() {

    override fun onCreate() {
        super.onCreate()

        // so this only works for debug build, and omitted for the release build
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

}