package com.sawaf.newsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sawaf.newsapp.R
import com.sawaf.newsapp.core.utils.*
import com.sawaf.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        observeEventOnce(viewModel.isLoading) {
            binding.apply {
                if (it) {
                    loadingProgress.toVisible()
                    textView.toGone()
                } else {
                    loadingProgress.toGone()
                    textView.toVisible()
                }
            }
        }

        observeEventOnce(viewModel.errorMsg) {
            it?.also { toast(it) }
        }
        // Event error example
/*
        homeViewModel.errorMsg.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.also {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        */

        binding.openDetails.setOnClickListener { // action
//            val navController = findNavController()
//            navController.navigate(R.id.action_homeFragment_to_detailsFragment)

            viewModel.loadData()
//            Log.d("sawafapp", "onClick: Hellllo")
            Timber.d("onClick: Hellllo")
        }
//        Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailsFragment)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}