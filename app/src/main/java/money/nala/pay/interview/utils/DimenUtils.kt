package money.nala.pay.interview.utils

import android.util.DisplayMetrics
import money.nala.pay.interview.app.NalaApp

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2/4/20.
 */
object DimenUtils {

    @JvmStatic
    fun displayMetrics(): DisplayMetrics {
        return NalaApp.appContext.resources.displayMetrics
    }

    @JvmStatic
    fun density(): Float {
        return displayMetrics().density
    }

    @JvmStatic
    fun scaledDensity(): Float {
        return displayMetrics().scaledDensity
    }

    @JvmStatic
    fun dp2px(dipValue: Float): Float {
        return dipValue * density() + 0.5f
    }

    @JvmStatic
    fun px2dp(pxValue: Float): Float {
        return pxValue / density() + 0.5f
    }

    @JvmStatic
    fun sp2px(spValue: Float): Float {
        return spValue * scaledDensity() + 0.5f
    }
}