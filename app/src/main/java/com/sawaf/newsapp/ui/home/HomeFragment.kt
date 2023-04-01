package com.sawaf.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawaf.newsapp.R
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

        val adapter = ArticlesAdapter() { item, _ ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_detailsFragment)
        }
        binding.articleRv.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        viewModel.articleList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        observeEventOnce(viewModel.isLoading) {
            binding.apply {
                if (it) {
                    loadingProgress.toVisible()
                    articleRv.toGone()
                } else {
                    loadingProgress.toGone()
                    articleRv.toVisible()
                }
            }
        }

        observeEventOnce(viewModel.errorMsg) {
            it?.also { toast(it) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}