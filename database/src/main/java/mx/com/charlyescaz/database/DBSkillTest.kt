package mx.com.charlyescaz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.com.charlyescaz.database.dao.SkillTestDao
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.database.models.WeatherBD


@Database(
        entities = [
            TestBD::class,
            WeatherBD::class,
        ],
        version = 3,
        exportSchema = false
)

abstract  class DBSkillTest: RoomDatabase() {

    companion object {
        lateinit var db: DBSkillTest
    }

    abstract fun skillTestDao(): SkillTestDao
}