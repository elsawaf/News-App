package com.sawaf.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sawaf.newsapp.core.utils.observeEventOnce
import com.sawaf.newsapp.core.utils.toGone
import com.sawaf.newsapp.core.utils.toVisible
import com.sawaf.newsapp.core.utils.toast
import com.sawaf.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

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

        binding.openDetails.setOnClickListener { // action
//            val navController = findNavController()
//            navController.navigate(R.id.action_homeFragment_to_detailsFragment)

            viewModel.loadData()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}