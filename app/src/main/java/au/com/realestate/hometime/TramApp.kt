package au.com.realestate.hometime

import android.app.Application
import au.com.realestate.hometime.di.AppComponent
import au.com.realestate.hometime.di.DaggerAppComponent
import au.com.realestate.hometime.di.ServiceModule

class TramApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerAppComponent.builder().serviceModule(ServiceModule()).build()
    }

    companion object {
        private var instance: TramApp? = null
        fun get(): TramApp = instance!!
    }
}