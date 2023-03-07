package com.archik.notes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.archik.notes.models.AppNote
import com.archik.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application): AndroidViewModel(application) {
  fun insert(note: AppNote, onSuccess: () -> Unit) {
    viewModelScope.launch(Dispatchers.Main) {
      REPOSITORY.insert(note) {
        onSuccess()
      }
    }
  }
}