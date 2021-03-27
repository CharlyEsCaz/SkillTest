package mx.com.charlyescaz.skilltest.di.modules


import dagger.Module
import dagger.Provides
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.ui.home.data.HomeRepository
import mx.com.charlyescaz.web.api.APISkilltest



@Module
class HomeModule {

    @Provides
    fun provideHomeRepository(): HomeRepository{
        return HomeRepository(APISkilltest, DBSkillTest.db.skillTestDao())
    }

}