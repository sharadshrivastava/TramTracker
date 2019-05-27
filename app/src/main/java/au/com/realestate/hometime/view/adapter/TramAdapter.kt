package au.com.realestate.hometime.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import au.com.realestate.hometime.R
import au.com.realestate.hometime.TramApp
import au.com.realestate.hometime.databinding.LayoutTramRowBinding
import au.com.realestate.hometime.service.models.Tram

import au.com.realestate.hometime.utils.Utils.dateFromDotNetDate
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TramAdapter(private var tramsList: MutableList<Tram?>?) : RecyclerView.Adapter<TramAdapter.TramViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramViewHolder {
        val binding = DataBindingUtil.inflate<LayoutTramRowBinding>(LayoutInflater.from(parent.context),
                R.layout.layout_tram_row, parent, false)

        return TramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TramViewHolder, position: Int) {
        holder.bindData(tramsList!![position])
    }

    override fun getItemCount(): Int {
        return if (tramsList == null) 0 else tramsList!!.size
    }

    inner class TramViewHolder(private var binding: LayoutTramRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(tram: Tram?) {
            val date = dateFromDotNetDate(tram?.predictedArrival!!)
            binding.tramTime.text = "${getFormattedDate(date)}  ${getTimeDifference(date)}"
            binding.tramInfo.text = "${itemView.context.getString(R.string.to)}: ${tram.destination}"
        }
    }

    fun getFormattedDate(date: Date): String {
        val format = SimpleDateFormat("h:mm a", Locale.US)
        return format.format(date)
    }

    fun getTimeDifference(date: Date): String {
        val millisDiff = Math.abs(date.time - System.currentTimeMillis())
        val minutesDiff = TimeUnit.MILLISECONDS.toMinutes(millisDiff).toInt()

        if (minutesDiff > 60) {
            val hours = minutesDiff / 60
            val minutes = minutesDiff % 60
            return TramApp.get().getString(R.string.after_arrival, hours, minutes)
        } else if (minutesDiff == 0) {
            return TramApp.get().getString(R.string.arriving)
        }
        return TramApp.get().getString(R.string.after_mins,minutesDiff)
    }

    fun setData(trams: List<Tram?>?) {
        if (tramsList != null) {
            tramsList!!.clear()
            tramsList!!.addAll(trams.orEmpty())
        } else {
            tramsList = trams?.toMutableList()
        }
        notifyDataSetChanged()
    }
}