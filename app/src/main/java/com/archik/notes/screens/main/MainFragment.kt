package com.archik.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.archik.notes.R
import com.archik.notes.databinding.FragmentMainBinding
import com.archik.notes.models.AppNote
import com.archik.notes.utilits.APP_ACTIVITY
import com.archik.notes.utilits.AppPreference

class MainFragment : Fragment() {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding
    get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

  private lateinit var viewModel: MainFragmentViewModel

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: MainAdapter
  private lateinit var observer: Observer<List<AppNote>>


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
    setHasOptionsMenu(true)

    adapter = MainAdapter()

    recyclerView = binding.recycleView
    recyclerView.adapter = adapter

    observer = Observer {
      val list = it.asReversed()

      adapter.listNotes = list
    }

    viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
    viewModel.allNotes.observe(this, observer)

    binding.btnAddNote.setOnClickListener {
      APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)

    inflater.inflate(R.menu.exit_action_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.btn_exit -> {
        viewModel.signOut()
        AppPreference.setInitUser(false)
        APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
      }
    }

    return super.onOptionsItemSelected(item)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null

    viewModel.allNotes.removeObserver(observer)
    recyclerView.adapter = null
  }

  companion object {
    fun click(note: AppNote) {
      val bundle = Bundle()

      bundle.putSerializable("note", note)

      APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }
  }
}