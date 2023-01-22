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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuselessfacts.R
import com.example.randomuselessfacts.app.App
import com.example.randomuselessfacts.databinding.FragmentFavouriteFactsBinding
import com.example.randomuselessfacts.di.ViewModelFactory
import com.example.randomuselessfacts.presentation.SwipeToDeleteCallback
import com.example.randomuselessfacts.presentation.activity.MainActivity
import com.example.randomuselessfacts.presentation.viewmodel.FavouriteFactsViewModel
import javax.inject.Inject

class FavouriteFactsFragment: Fragment(R.layout.fragment_favourite_facts) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: FavouriteFactsViewModel
    private var _binding: FragmentFavouriteFactsBinding? = null
    private val binding get() = _binding!!
    private val swipeHandler = object : SwipeToDeleteCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.removeFavouriteFact(viewHolder.position)
        }
    }

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
            ViewModelProvider(this, viewModelFactory)[FavouriteFactsViewModel::class.java]
        _binding = FragmentFavouriteFactsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.listFavourite)
        observers()
        initBarMenu()
        return binding.root
    }

    private fun observers() {
        viewModel.factListFavourite.observe(viewLifecycleOwner) { factListHistory ->
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
                    viewModel.removeFavouriteFacts()
                else
                    findNavController().navigateUp()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}