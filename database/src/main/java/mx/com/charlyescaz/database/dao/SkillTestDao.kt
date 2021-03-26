package mx.com.charlyescaz.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.database.models.WeatherBD

@Dao
interface SkillTestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(vararg test: TestBD): Completable

    @Query("SELECT * FROM test")
    fun getAll(): Maybe<List<TestBD>>

    @Query("SELECT COUNT(*) FROM test")
    fun count(): Single<Int>

    @Query("SELECT * FROM test WHERE idLocalDB = :idLocal")
    fun findById(idLocal: Long): Single<TestBD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertWeather(vararg weather: WeatherBD): Completable

    @Query("SELECT * FROM weather WHERE id = :id")
    fun findWeatherById(id: Int): Single<WeatherBD>

    @Query("SELECT * FROM weather WHERE fkIdTest = :testId")
    fun findWeathersById(testId: Int): Maybe<List<WeatherBD>>
}