package com.archik.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.archik.notes.R
import com.archik.notes.databinding.FragmentStartBinding
import com.archik.notes.utilits.APP_ACTIVITY
import com.archik.notes.utilits.TYPE_ROOM

class StartFragment : Fragment() {

  private var _binding: FragmentStartBinding? = null
  private val binding: FragmentStartBinding
    get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

  private lateinit var viewModel: StartFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentStartBinding.inflate(layoutInflater, container, false)

    return binding.root
  }

  override fun onStart() {
    super.onStart()

    initialization()
  }

  private fun initialization() {
    viewModel = ViewModelProvider(this)[StartFragmentViewModel::class.java]

    with(binding) {
      btnRoom.setOnClickListener {
        viewModel.initDatabase(TYPE_ROOM) {
          APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}