package com.archik.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.archik.notes.R
import com.archik.notes.databinding.FragmentStartBinding
import com.archik.notes.utilits.*

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

    viewModel = ViewModelProvider(this)[StartFragmentViewModel::class.java]

    if (AppPreference.getInitUser()) {
      viewModel.initDatabase(AppPreference.getTypeDB()) {
        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
      }
    } else {
      initialization()
    }
  }

  private fun initialization() {
    with(binding) {
      btnRoom.setOnClickListener {
        viewModel.initDatabase(TYPE_ROOM) {
          AppPreference.setInitUser(true)
          AppPreference.setTypeDB(TYPE_ROOM)

          APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
        }
      }
      btnFirebase.setOnClickListener {
        binding.inputEmail.visibility = View.VISIBLE
        binding.inputPassword.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.VISIBLE
        binding.btnLogin.setOnClickListener {
          val inputEmail = binding.inputEmail.text.toString()
          val inputPassword = binding.inputPassword.text.toString()

          if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
            EMAIL = inputEmail
            PASSWORD = inputPassword

            viewModel.initDatabase(TYPE_FIREBASE) {3

              AppPreference.setInitUser(true)
              AppPreference.setTypeDB(TYPE_FIREBASE)

              APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
          } else {
            showToast(getString(R.string.toast_wrong_enter))
          }
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}