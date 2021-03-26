package mx.com.charlyescaz.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherBD(
        @PrimaryKey(autoGenerate = true)
        val idLocalDB: Long? = null,
        val id: Int?,
        val main: String?,
        val description: String?,
        val icon: String?,
        var fkIdTest: Long?
)