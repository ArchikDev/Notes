package com.archik.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
  private val context = application

  fun initDatabase(type: String) {}
}