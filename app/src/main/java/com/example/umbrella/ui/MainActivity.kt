package com.example.umbrella.ui

import android.net.wifi.rtt.CivicLocationKeys.CITY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.exam.GalleryFragment
import com.example.umbrella.R
import com.example.umbrella.fragments.BottomSheet_Fragment
import com.example.umbrella.fragments.FirstFragment
import com.example.umbrella.fragments.StartFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_navigation.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, StartFragment())
            .commit()



        }
    }


