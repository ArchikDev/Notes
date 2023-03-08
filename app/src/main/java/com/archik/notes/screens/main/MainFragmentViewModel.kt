package com.archik.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.archik.notes.utilits.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
  val allNotes = REPOSITORY.allNotes
}