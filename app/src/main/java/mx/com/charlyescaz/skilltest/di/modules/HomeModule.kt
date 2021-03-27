package mx.com.charlyescaz.skilltest.di.modules


import androidx.room.Room
import dagger.Module
import dagger.Provides
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.SkillTestApp
import mx.com.charlyescaz.skilltest.ui.details.data.OtherDetailsRepository
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.skilltest.ui.home.data.MapsViewRepository
import mx.com.charlyescaz.skilltest.ui.home.data.OtherRepository
import mx.com.charlyescaz.web.api.APISkilltest
import javax.inject.Singleton


@Module
class HomeModule(private val app: SkillTestApp) {

    var db = Room
        .databaseBuilder(
            app,
            DBSkillTest::class.java,
            "skilltest.db"
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository =  HomeRepository(APISkilltest, db.skillTestDao())

    @Provides
    @Singleton
    fun provideOtherRepository(): OtherRepository  = OtherRepository(db.skillTestDao())

    @Provides
    @Singleton
    fun provideMapsViewRepository(): MapsViewRepository =  MapsViewRepository(db.skillTestDao())

    @Provides
    @Singleton
    fun provideOtherDetailsRepository(): OtherDetailsRepository =  OtherDetailsRepository(db.skillTestDao())

}