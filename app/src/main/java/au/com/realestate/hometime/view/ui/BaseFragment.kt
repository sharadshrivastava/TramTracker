package au.com.realestate.hometime.view.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import au.com.realestate.hometime.utils.Utils
import au.com.realestate.hometime.view.adapter.TramAdapter
import au.com.realestate.hometime.view.adapter.VerticalSpaceItemDecoration

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    protected fun setupListView(listView: RecyclerView?, adapter: TramAdapter?) {
        listView?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        listView?.layoutManager = mLayoutManager
        listView?.adapter = adapter
        listView?.addItemDecoration(VerticalSpaceItemDecoration(Utils.pxFromDp(context, 1f).toInt()))
    }
}

