package com.example.randomuselessfacts.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.randomuselessfacts.R
import com.example.randomuselessfacts.app.App
import com.example.randomuselessfacts.databinding.FragmentHistoryFactsBinding
import com.example.randomuselessfacts.di.ViewModelFactory
import com.example.randomuselessfacts.presentation.activity.MainActivity
import com.example.randomuselessfacts.presentation.viewmodel.HistoryFactsViewModel
import javax.inject.Inject

class HistoryFactsFragment: Fragment(R.layout.fragment_history_facts) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: HistoryFactsViewModel
    private var _binding: FragmentHistoryFactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity as MainActivity).applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this, viewModelFactory)[HistoryFactsViewModel::class.java]
        _binding = FragmentHistoryFactsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observers()
        initBarMenu()
        return binding.root
    }

    private fun observers() {
        viewModel.factListHistory.observe(viewLifecycleOwner) { factListHistory ->
            if (!factListHistory.isNullOrEmpty()) viewModel.setAdapter()
        }
    }

    private fun initBarMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_delete, menu)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.delete)
                    viewModel.removeHistoryFacts()
                else
                    findNavController().navigateUp()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}