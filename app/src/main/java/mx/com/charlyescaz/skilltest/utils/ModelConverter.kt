package mx.com.charlyescaz.skilltest.utils

import com.google.gson.Gson
import mx.com.charlyescaz.database.models.TestBD
import mx.com.charlyescaz.database.models.WeatherBD
import mx.com.charlyescaz.skilltest.models.Test
import mx.com.charlyescaz.skilltest.models.Weather

object ModelConverter {
    val gson = Gson()

    fun Test.toTestBB() = toModel(this, TestBD::class.java)
    fun TestBD.toTest() = toModel(this, Test::class.java)

    fun Weather.toWeatherBD() = toModel(this, WeatherBD::class.java)
    fun WeatherBD.toWeather() = toModel(this, Weather::class.java)

    fun convertStringToTestModel(data: String): Test{
        val firstIndex = data.indexOf("{")
        val jsonString = data.substring(firstIndex, data.length - 1)
        return Gson().fromJson(jsonString, Test::class.java)
    }

    fun <T> Any.toModel(ob: Any, cl: Class<T>): T {
        val data = gson.toJsonTree(ob).asJsonObject
        return gson.fromJson(data, cl)
    }

    fun <T> List<Any>.toListModel(model: Class<T>): List<T> {
        val list = mutableListOf<T>()
        this.forEach { list.add(it.toModel(it, model)) }
        return list
    }
}