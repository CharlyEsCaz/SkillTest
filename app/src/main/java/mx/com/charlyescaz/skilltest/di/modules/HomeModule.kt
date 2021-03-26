package mx.com.charlyescaz.skilltest.di.modules


import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.ui.home.contract.HomeContract
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.presenter.HomePresenter
import mx.com.charlyescaz.skilltest.ui.home.view.HomeActivity
import mx.com.charlyescaz.web.api.APISkilltest
import javax.inject.Singleton


@Module
abstract class HomeModule {

//    @Binds
//    abstract fun view(activity: HomeActivity?): HomeContract.View?
//
//    @Binds
//    abstract fun context(context: HomeActivity?): Context

//    @Provides
//    fun provideHomePresenter(context: Context, repository: HomeRepository, view: HomeContract.View) = HomePresenter(context, view , repository)
//
//    @Provides
//
//    fun provideHomeRepository() = HomeRepository( APISkilltest , DBSkillTest.db.skillTestDao() )

//    @Provides
//    open fun provideHomePresenter(context: Context, repository: HomeRepository, view: HomeContract.View): HomeContract.Presenter{
//        return HomePresenter(context, view , repository)
//    }
//
//
//    @Provides
//    open fun provideHomeRepository(): HomeRepository{
//        return HomeRepository(APISkilltest, DBSkillTest.db.skillTestDao())
//    }



}