package au.com.realestate.hometime.utils

import android.content.Context
import java.util.*

object Utils {

    fun dateFromDotNetDate(dotNetDate: String): Date {
        val startIndex = dotNetDate.indexOf("(") + 1
        val endIndex = dotNetDate.indexOf("+")
        val date = dotNetDate.substring(startIndex, endIndex)
        return Date(date.toLong())
    }

    fun pxFromDp(context: Context?, dp: Float): Float {
        return dp * context?.resources?.displayMetrics?.density!!
    }
}