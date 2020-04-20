package money.nala.pay.interview.utils

import android.content.Context
import money.nala.pay.interview.app.NalaApp
import java.io.InputStream
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun createHumanReadableTimestamp(timeStamp: Long, format: String): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(Date(timeStamp))
    }

    /**
     * @throws IOException, JSONException
     */
    fun readFromAssetFile(context: Context = NalaApp.appContext, fileName: String): String {
        return readFromInputStream(context.assets.open(fileName))
    }

    /**
     * @throws IOException, JSONException
     */
    fun readFromInputStream(inputStream: InputStream): String {
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return buffer.toString(Charset.forName("UTF-8"))
    }
}