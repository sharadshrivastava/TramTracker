package au.com.realestate.hometime.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import au.com.realestate.hometime.TramApp
import au.com.realestate.hometime.service.Resource
import au.com.realestate.hometime.service.TramsRepository
import au.com.realestate.hometime.service.models.Token
import au.com.realestate.hometime.service.models.Tram

import javax.inject.Inject

class TramListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var tramsRepository: TramsRepository

    init {
        TramApp.get().component.inject(this)
    }

    fun getToken(): LiveData<Resource<Token?>> = tramsRepository.getToken()

    fun getTrams(stopId: String, token: String?): LiveData<Resource<List<Tram?>?>> {
        return tramsRepository.getTrams(stopId, token)
    }
}