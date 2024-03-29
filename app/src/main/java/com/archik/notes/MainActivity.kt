package com.archik.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.archik.notes.databinding.ActivityMainBinding
import com.archik.notes.utilits.APP_ACTIVITY
import com.archik.notes.utilits.AppPreference

class MainActivity : AppCompatActivity() {

  private lateinit var mToolbar: Toolbar
  lateinit var navController: NavController

  private var _binding: ActivityMainBinding? = null
  private val binding: ActivityMainBinding
    get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    _binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)

    APP_ACTIVITY = this

    mToolbar = binding.toolbar

    navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    setSupportActionBar(mToolbar)

    title = getString(R.string.title)

    AppPreference.getPrefderence(this)
  }

  override fun onDestroy() {
    super.onDestroy()

    _binding = null
  }
}