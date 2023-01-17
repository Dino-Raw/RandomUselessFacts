package com.example.randomuselessfacts.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.randomuselessfacts.R
import com.example.randomuselessfacts.app.App
import com.example.randomuselessfacts.databinding.FragmentFactBinding
import com.example.randomuselessfacts.di.ViewModelFactory
import com.example.randomuselessfacts.presentation.activity.MainActivity
import com.example.randomuselessfacts.presentation.viewmodel.FactViewModel
import javax.inject.Inject

class FactFragment: Fragment(R.layout.fragment_fact) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: FactViewModel
    private var _binding: FragmentFactBinding? = null
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
            ViewModelProvider(this, viewModelFactory)[FactViewModel::class.java]
        _binding = FragmentFactBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initButton()
        return binding.root
    }

    private fun initButton() {
        binding.openHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_fact_to_fragment_history_facts)
        }

        binding.openFavouriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_fact_to_fragment_favourite_facts)
        }
    }
}