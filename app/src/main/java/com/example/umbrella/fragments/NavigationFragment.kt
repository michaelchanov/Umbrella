package com.example.umbrella.fragments

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.umbrella.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_navigation.*


class NavigationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navigationFragment = NavigationFragment()
        val nameEditText = nameEditText.text
        val lastnameEditText = lastNameEditText.text
        val mediaPlayer: MediaPlayer? = MediaPlayer.create(context,R.raw.drop_song)


        button_register.setOnClickListener {
            mediaPlayer?.start()
            navigationFragment.arguments=Bundle().also {
                it.putInt("key",1)
                Toast.makeText(activity, "Welcome, $nameEditText $lastnameEditText",Toast.LENGTH_LONG).show()
            }
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer,FirstFragment())
                ?.commit()
        }

    }

}




