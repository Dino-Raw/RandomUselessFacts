package com.example.randomuselessfacts.presentation.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.compose.material3.Snackbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.randomuselessfacts.R
import com.example.randomuselessfacts.app.App
import com.example.randomuselessfacts.databinding.FragmentFactBinding
import com.example.randomuselessfacts.di.ViewModelFactory
import com.example.randomuselessfacts.presentation.activity.MainActivity
import com.example.randomuselessfacts.presentation.viewmodel.FactViewModel
import com.google.android.material.elevation.SurfaceColors
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FactFragment: Fragment(R.layout.fragment_fact) {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: FactViewModel
    private var _binding: FragmentFactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity as MainActivity).applicationContext as App).appComponent.inject(this)
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this, viewModelFactory)[FactViewModel::class.java]
        _binding = FragmentFactBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initBarMenu()
        observers()

        return binding.root
    }

    private fun observers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message != "")
                Snackbar
                    .make(binding.root, message, Snackbar.LENGTH_SHORT)
                    .setAnimationMode(ANIMATION_MODE_SLIDE)
                    .setBackgroundTint(SurfaceColors.SURFACE_5.getColor(requireContext()))
                    .setTextColor(resources.getColor(R.color.black))
                    .setAnchorView(binding.layoutButton)
                    .show()
        }
    }

    private fun initBarMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_facts, menu)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.openHistoryButton -> {
                        findNavController().navigate(R.id.action_fragment_fact_to_fragment_history_facts)
                        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        true
                    }
                    R.id.openFavouriteButton -> {
                        findNavController().navigate(R.id.action_fragment_fact_to_fragment_favourite_facts)
                        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}