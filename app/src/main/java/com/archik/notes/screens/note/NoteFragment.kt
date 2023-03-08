package com.archik.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.archik.notes.R
import com.archik.notes.databinding.FragmentMainBinding
import com.archik.notes.databinding.FragmentNoteBinding
import com.archik.notes.models.AppNote
import com.archik.notes.screens.main.MainAdapter
import com.archik.notes.screens.main.MainFragmentViewModel
import com.archik.notes.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {
  private var _binding: FragmentNoteBinding? = null
  private val binding: FragmentNoteBinding
    get() = _binding ?: throw RuntimeException("FragmentNoteBinding is null")

  private lateinit var viewModel: NoteFragmentViewModel
  private lateinit var currentNote: AppNote


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)

    currentNote = arguments?.getSerializable("note") as AppNote

    return binding.root
  }

  override fun onStart() {
    super.onStart()

    initialization()
  }

  private fun initialization() {
    setHasOptionsMenu(true)
    binding.noteText.text = currentNote.text
    binding.noteName.text = currentNote.name

    viewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)

    inflater.inflate(R.menu.note_action_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.btn_delete -> {
        viewModel.delete(currentNote) {
          APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
        }
      }
    }

    return super.onOptionsItemSelected(item)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}