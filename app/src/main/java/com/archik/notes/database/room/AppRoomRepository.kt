package com.archik.notes.database.room

import androidx.lifecycle.LiveData
import com.archik.notes.database.DatabaseRepository
import com.archik.notes.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao): DatabaseRepository {
  override val allNotes: LiveData<List<AppNote>>
    get() = appRoomDao.getAllNotes()

  override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
    appRoomDao.insert(note)
    onSuccess()
  }

  override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
    appRoomDao.delete(note)
    onSuccess()
  }

  override fun signOut() {
    super.signOut()
  }
}