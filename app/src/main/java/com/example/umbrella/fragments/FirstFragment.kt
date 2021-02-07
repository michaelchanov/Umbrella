package com.example.umbrella.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.exam.GalleryFragment
import com.example.umbrella.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


class FirstFragment : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    public var CITY = "Moscow"
    var fragmentGallery = GalleryFragment()
    val API = "5fc05d7c39b4fe7adeec21be43dad804"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        weatherTask().execute()

        searchCity.setOnClickListener {
            CITY = editTextSearch.text.toString()
            Log.d("smt2", "$CITY")
            fragmentGallery.arguments = Bundle().also {
                it.putString("key", "$CITY")
            }
            weatherTask().execute()

        }
        info_bt.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, InfoFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        umbrella_bn.setOnClickListener {

            fragmentGallery.arguments = Bundle().also {
                it.putString("key", "$CITY")
            }

            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, fragmentGallery)
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {


        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")

                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
                requireActivity().findViewById<TextView>(R.id.address).text = address
                requireActivity().findViewById<TextView>(R.id.status).text =
                    weatherDescription.capitalize()
                requireActivity().findViewById<TextView>(R.id.temp).text = temp
                requireActivity().findViewById<TextView>(R.id.minTemp).text = tempMin
                requireActivity().findViewById<TextView>(R.id.maxTemp).text = tempMax
                requireActivity().findViewById<TextView>(R.id.sunrise).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                requireActivity().findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                requireActivity().findViewById<TextView>(R.id.wind).text = windSpeed
                requireActivity().findViewById<TextView>(R.id.pressure).text = pressure
                requireActivity().findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                requireActivity().findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Please type the city right ",
                    Toast.LENGTH_LONG
                ).show()
                editTextSearch.text = null
            }

        }
    }
}




