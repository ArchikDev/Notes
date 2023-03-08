package com.archik.notes.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.archik.notes.models.AppNote
import com.archik.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application) {
  fun delete(note: AppNote, onSuccess: () -> Unit) {
    viewModelScope.launch (Dispatchers.Main) {
      REPOSITORY.delete(note) {
        onSuccess()
      }
    }
  }
}