package com.exam

import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_first.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umbrella.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    var CITY = ""
    var url = ""
    var transformedCity = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CITY = arguments?.getString("key") ?: ""
        transformedCity = CITY.toLowerCase()
        Log.d("smt2","$CITY")
        imagesTask().execute()
        Log.d("smt2","dvvx")
        url = "https://api.teleport.org/api/urban_areas/slug:$transformedCity/images/"
    }


    inner class imagesTask() : AsyncTask<String, Void, String>() {


        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            Log.d("smt2","doinback")
            try {
                response =
                    URL("https://api.teleport.org/api/urban_areas/slug:$transformedCity/images/").readText(
                        Charsets.UTF_8
                    )

            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            Log.d("smt2","onpostexecute")
            super.onPostExecute(result)
            try {
                Log.d("smt2","onpostexecuteTRY")
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                Log.d("smt2","json done")
                val photos = jsonObj.getJSONArray("photos").getJSONObject(0)
                Log.d("smt2","photos done")
                val image = photos.getJSONObject("image")
                Log.d("smt2","image done")
                val mobile = image.getString("mobile")

                val imageUrl = "$mobile"

                Log.d("smt2","im done $mobile")


                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(cityImage)
            } catch (e: Exception) {

            }

        }
    }
}