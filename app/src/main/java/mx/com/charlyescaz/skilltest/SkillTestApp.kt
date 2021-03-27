package mx.com.charlyescaz.skilltest

import android.app.Application
import androidx.room.Room
import mx.com.charlyescaz.database.DBSkillTest
import mx.com.charlyescaz.skilltest.di.components.DaggerHomeComponent
import mx.com.charlyescaz.skilltest.di.components.HomeComponent

class SkillTestApp: Application() {

    private lateinit var homeComponent: HomeComponent

    override fun onCreate() {
        super.onCreate()
        initComponents()

    }

    private fun initComponents() {
        DBSkillTest.db = Room
                .databaseBuilder(this,
                        DBSkillTest::class.java,
                        "skilltest.db")
                .fallbackToDestructiveMigration()
                .build()

        homeComponent = DaggerHomeComponent
            .builder()
            .build()
    }

    fun getHomeComponent() = homeComponent

}