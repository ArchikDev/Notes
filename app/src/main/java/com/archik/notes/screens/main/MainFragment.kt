package com.archik.notes.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.archik.notes.R
import com.archik.notes.databinding.FragmentMainBinding
import com.archik.notes.utilits.APP_ACTIVITY

class MainFragment : Fragment() {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding
    get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

  private lateinit var viewModel: MainFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

    return binding.root
  }

  override fun onStart() {
    super.onStart()

    initialization()
  }

  private fun initialization() {
    viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]

    binding.btnAddNote.setOnClickListener {
      APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}