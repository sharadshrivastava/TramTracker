package au.com.realestate.hometime.utils

import android.content.Context
import au.com.realestate.hometime.R
import au.com.realestate.hometime.TramApp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    fun dateFromDotNetDate(dotNetDate: String): Date {
        val startIndex = dotNetDate.indexOf("(") + 1
        val endIndex = dotNetDate.indexOf("+")
        val date = dotNetDate.substring(startIndex, endIndex)
        return Date(date.toLong())
    }

    fun dpFromPx(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun pxFromDp(context: Context?, dp: Float): Float {
        return dp * context?.resources?.displayMetrics?.density!!
    }
}