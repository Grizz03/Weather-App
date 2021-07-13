package com.stevew.weatherapplivedata

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stevew.weatherapplivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // checks if location is enabled, if not then it will toast
        if(!isLocationEnabled()){
            Toast.makeText(this, "Your location provider is turned off. Please turn it on.", Toast.LENGTH_SHORT).show()
            // brings user to allow location
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Your location provider is already turned ON.", Toast.LENGTH_SHORT).show()
        }

    }

    // gets location
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}