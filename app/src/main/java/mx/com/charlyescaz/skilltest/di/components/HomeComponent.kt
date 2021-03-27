package mx.com.charlyescaz.skilltest.di.components

import dagger.Component
import mx.com.charlyescaz.skilltest.di.modules.HomeModule
import mx.com.charlyescaz.skilltest.ui.details.view.OtherDetailsActivity
import mx.com.charlyescaz.skilltest.ui.home.view.HomeActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)

    fun inject(otherActivity: OtherDetailsActivity)
}