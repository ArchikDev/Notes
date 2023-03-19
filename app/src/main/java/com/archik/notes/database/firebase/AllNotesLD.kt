package com.archik.notes.database.firebase

import androidx.lifecycle.LiveData
import com.archik.notes.models.AppNote
import com.archik.notes.utilits.REF_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotesLD: LiveData<List<AppNote>>() {

  private val listener = object: ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
      value = snapshot.children.map {
        it.getValue(AppNote::class.java)?: AppNote()
      }
    }

    override fun onCancelled(error: DatabaseError) {}
  }

  override fun onActive() {
    REF_DATABASE.addValueEventListener(listener)

    super.onActive()
  }

  override fun onInactive() {
    REF_DATABASE.removeEventListener(listener)

    super.onInactive()
  }
}