package com.example.umbrella.fragments

import android.Manifest
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.umbrella.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_navigation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class NavigationFragment : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameEditText = nameEditText.text
        val lastnameEditText = lastNameEditText.text
        val mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.drop_song)



            button_register.setOnClickListener {
                if (nameEditText.isEmpty() || lastnameEditText.isEmpty()) {

                    Toast.makeText(
                        requireContext(),
                        "Please enter text in Username/Ln",
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val objectAnimator = ObjectAnimator.ofFloat(it, "translationY", 520f)
                objectAnimator.duration = 700
                objectAnimator.start()
                Handler().postDelayed({ mediaPlayer?.start() }, 500)

                Handler().postDelayed({
                    Toast.makeText(
                        it.context, "Welcome " + " $nameEditText $lastnameEditText", Toast.LENGTH_LONG).show()
                }, 1500)

                Handler().postDelayed({
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, FirstFragment())
                    ?.addToBackStack(null)
                    ?.commit()},1500)
            }
            userPhoto.setOnClickListener {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){
                        //permission denied
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //system OS is < Marshmallow
                    pickImageFromGallery();
                }
            }
        }
    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            userPhoto.setImageURI(data?.data)
        }
    }
}



