package mx.com.charlyescaz.skilltest.models

class Test(
        val coord: Location? = null,
        val weather: List<Weather>? = null,
        val base: String = "",
        val main: Main? = null,
        val visibility: Int = 0,
        val wind: Wind? = null,
        val clouds: Clouds? = null,
        val dt: Int = 0,
        val sys: Sys? = null,
        val timezone: Int = 0,
        val id: Int = 0,
        val name: String = "",
        val cod: Int = 0,
)