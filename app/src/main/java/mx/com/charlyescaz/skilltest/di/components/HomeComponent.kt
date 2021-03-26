package mx.com.charlyescaz.skilltest.di.components

import dagger.Component
import mx.com.charlyescaz.skilltest.di.modules.HomeModule
import mx.com.charlyescaz.skilltest.ui.home.view.HomeActivity

@Component(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}