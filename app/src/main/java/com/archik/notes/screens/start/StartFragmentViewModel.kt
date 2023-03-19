package com.archik.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.archik.notes.database.firebase.AppFirebaseRepository
import com.archik.notes.database.room.AppRoomDatabase
import com.archik.notes.database.room.AppRoomRepository
import com.archik.notes.utilits.REPOSITORY
import com.archik.notes.utilits.TYPE_FIREBASE
import com.archik.notes.utilits.TYPE_ROOM
import com.archik.notes.utilits.showToast

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
  private val context = application

  fun initDatabase(type: String, onSuccess: () -> Unit) {
    when(type) {
      TYPE_ROOM -> {
        val dbRoom = AppRoomDatabase.getInstance(context)

        val dao = dbRoom.appRoomDao()

        REPOSITORY = AppRoomRepository(dao)

        onSuccess()
      }

      TYPE_FIREBASE -> {
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase({ onSuccess() },{ showToast(it) })
      }
    }
  }
}