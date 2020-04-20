package money.nala.pay.interview.data.settings

import android.content.SharedPreferences
import androidx.core.content.edit

class Settings(
        val settings: SharedPreferences
) {

    fun shouldShowBalance(): Boolean {
        return settings.getBoolean(SettingsConstants.SHOULD_SHOW_BALANCE, true)
    }

    fun setShouldShowBalance(should_show_balance: Boolean) {
        settings.edit {
            putBoolean(SettingsConstants.SHOULD_SHOW_BALANCE, should_show_balance)
        }
    }

    fun clearData() {
        settings.edit { clear() }
    }
}

object SettingsConstants {
    const val SHOULD_SHOW_BALANCE = "should_show_balance"
}