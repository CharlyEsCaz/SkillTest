package mx.com.charlyescaz.skilltest.di.modules


import dagger.Module
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.SkillTestApp


@Module
class HomeModule(private val app: SkillTestApp, private val db: DBSkillTest) {
//
//    @Binds
//    abstract fun view(mainActivity: HomeActivity?): HomeContract.View?

//    @Provides
//    @Singleton
//    fun provideApplicationContext(): Context = app
//
//    @Provides
//    @Singleton
//    fun provideHomeView() = HomeActivity()
//
//    @Provides
//    @Singleton
//    fun provideHomeRepository() = HomeRepository( APISkilltest , db.skillTestDao() )
//
//    @Provides
//    @Singleton
//    fun provideHomePresenter(repository: HomeRepository, view: HomeView) = HomePresenter(app, view , repository)


}