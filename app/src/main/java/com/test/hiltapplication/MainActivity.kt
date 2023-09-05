package com.test.hiltapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.test.hiltapplication.databinding.ActivityMainBinding
import com.test.hiltapplication.utils.Status
import com.test.hiltapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.pbDog.indeterminateDrawable.setColorFilter(0xFFFF0000.toInt(),android.graphics.PorterDuff.Mode.MULTIPLY);
        fetchData()
        _binding.imgRefresh.setOnClickListener {
            fetchResponse()
        }
    }

    private fun fetchData() {
        fetchResponse()
        mainViewModel.response.observe(this, Observer {
            Log.d("TAGG", "response $it")
            when (it.status) {
                Status.LOADING -> {
                    _binding.pbDog.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.let {
                        //val bmp = BitmapFactory.decodeByteArray(it.data, 0, it.data.toString().length)
                        _binding.imgDog.load(
                            it.url
                        ) {
                            //transformations(RoundedCornersTransformation(16f))
                            transformations(CircleCropTransformation())
                        }
                    }
                    _binding.pbDog.visibility = View.GONE
                }

                Status.ERROR -> {
                    _binding.pbDog.visibility = View.GONE
                    Toast.makeText(
                        this,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun fetchResponse() {
        mainViewModel.fetchDogResponse()
    }
}