package mx.com.charlyescaz.skilltest

import android.app.Application
import androidx.room.Room
import mx.com.charlyescaz.database.DBSkillTest

class SkillTestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        DBSkillTest.db = Room
                .databaseBuilder(this,
                        DBSkillTest::class.java,
                        "skilltest.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}