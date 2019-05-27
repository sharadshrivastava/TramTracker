package au.com.realestate.hometime.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import au.com.realestate.hometime.R
import au.com.realestate.hometime.databinding.FragmentTramListBinding
import au.com.realestate.hometime.service.Resource
import au.com.realestate.hometime.service.TramsApi.Companion.NORTH_CODE
import au.com.realestate.hometime.service.TramsApi.Companion.SOUTH_CODE
import au.com.realestate.hometime.service.models.Tram
import au.com.realestate.hometime.view.adapter.TramAdapter
import au.com.realestate.hometime.viewmodel.TramListViewModel

class TramListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentTramListBinding
    private lateinit var viewModel: TramListViewModel

    private var northAdapter: TramAdapter? = null
    private var southAdapter: TramAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TramListViewModel::class.java)

        observeLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tram_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.swiperefresh.setOnRefreshListener(this)
        setupNorthList(null)
        setupSouthList(null)
    }

    private fun setupNorthList(list: MutableList<Tram?>?) {
        if (northAdapter == null) {
            binding.isLoading = true
            northAdapter = TramAdapter(list)
        }
        setupListView(binding.tramListNorth, northAdapter)
    }

    private fun setupSouthList(list: MutableList<Tram?>?) {
        if (southAdapter == null) {
            southAdapter = TramAdapter(list)
        }
        setupListView(binding.tramListSouth, southAdapter)
    }

    private fun observeLiveData() {
        observeTokenLiveData()
    }

    private fun observeTokenLiveData() {
        viewModel.getToken().observe(this, Observer { resource ->
            if (resource?.status == Resource.Status.SUCCESS) {
                val token = resource.data
                observeTramsLiveData(NORTH_CODE, token?.value)
                observeTramsLiveData(SOUTH_CODE, token?.value)
            } else {
                binding.isLoading = false
                Snackbar.make(binding.root, resource?.message
                        ?: getString(R.string.error), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun observeTramsLiveData(stopId: String, token: String?) {
        viewModel.getTrams(stopId, token).observe(this, Observer { resource ->
            if (resource?.status == Resource.Status.SUCCESS) {
                binding.isLoading = false
                binding.swiperefresh.isRefreshing = false

                if (stopId == NORTH_CODE) {
                    refreshNorthList(resource.data)
                } else {
                    refreshSouthList(resource.data)
                }
            } else {
                binding.isLoading = false
                Snackbar.make(binding.root, resource?.message
                        ?: getString(R.string.error), Snackbar.LENGTH_INDEFINITE).show()
            }
        })
    }

    private fun refreshNorthList(list: List<Tram?>?) {
        northAdapter?.setData(list)
    }

    private fun refreshSouthList(list: List<Tram?>?) {
        southAdapter?.setData(list)
    }

    override fun onRefresh() {
        observeLiveData()
    }

    companion object {
        fun create(): TramListFragment = TramListFragment()
    }
}