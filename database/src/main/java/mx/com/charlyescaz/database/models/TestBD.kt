package mx.com.charlyescaz.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class TestBD(
        @PrimaryKey(autoGenerate = true)
        val idLocalDB: Long? = null,

        @Embedded
        val coord: LocationBD? = null,
        
        val base: String = "",

        @Embedded
        val main: MainBD? = null,

        val visibility: Int = 0,

        @Embedded
        val wind: WindBD? = null,

        @Embedded
        val clouds: CloudsBD? = null,

        val dt: Int = 0,

        @Embedded
        val sys: SysBD? = null,

        val timezone: Int = 0,

        val id: Int = 0,

        val name: String = "",

        val cod: Int = 0,
)