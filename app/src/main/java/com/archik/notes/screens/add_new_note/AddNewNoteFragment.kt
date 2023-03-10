package com.archik.notes.screens.add_new_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.archik.notes.R
import com.archik.notes.databinding.FragmentAddNewNoteBinding
import com.archik.notes.models.AppNote
import com.archik.notes.utilits.APP_ACTIVITY
import com.archik.notes.utilits.showToast

class AddNewNoteFragment : Fragment() {

  private var _binding: FragmentAddNewNoteBinding? = null
  private val binding: FragmentAddNewNoteBinding
    get() = _binding ?: throw RuntimeException("FragmentAddNewNoteBinding is null")

  private lateinit var viewModel: AddNewNoteViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)

    return binding.root
  }

  override fun onStart() {
    super.onStart()

    initialization()
  }

  private fun initialization() {
    viewModel = ViewModelProvider(this)[AddNewNoteViewModel::class.java]

    binding.btnAddNote.setOnClickListener {
      val name = binding.inputNameNote.text.toString()
      val text = binding.inputTextNote.text.toString()

      if (name.isEmpty()) {
        showToast(getString(R.string.toast_enter_name))
      } else {
        viewModel.insert(AppNote(name = name, text = text)) {
          APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}