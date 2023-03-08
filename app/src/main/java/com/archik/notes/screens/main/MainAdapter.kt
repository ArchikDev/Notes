package com.archik.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.archik.notes.R
import com.archik.notes.models.AppNote

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainHolder>() {

  var listNotes = listOf<AppNote>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  class MainHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameNote = view.findViewById<TextView>(R.id.item_note_name)
    val textNote = view.findViewById<TextView>(R.id.item_note_text)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    // LayoutInflater - из макета создаёт view
    val view = LayoutInflater.from(parent.context).inflate(
      R.layout.note_item,
      parent,
      false
    )

    return MainHolder(view)
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    holder.textNote.text = listNotes[position].text
    holder.nameNote.text = listNotes[position].name
  }

  override fun getItemCount(): Int {
    return listNotes.size
  }
}