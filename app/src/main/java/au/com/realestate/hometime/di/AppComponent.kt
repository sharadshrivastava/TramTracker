package au.com.realestate.hometime.di

import au.com.realestate.hometime.viewmodel.TramListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ServiceModule::class))
interface AppComponent {

    fun inject(vm: TramListViewModel)
}