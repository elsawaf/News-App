package com.sawaf.newsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawaf.newsapp.databinding.FragmentDashboardBinding
import com.sawaf.newsapp.ui.home.HeadlinesAdapter
import com.sawaf.newsapp.ui.home.HomeFragmentDirections
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = HeadlinesAdapter({ item, _ ->
            val navController = findNavController()
            val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(item)
            navController.navigate(action)
        },
            viewModel::removeArticle
        )
        binding.headlineRv.layoutManager = LinearLayoutManager(context)
        binding.headlineRv.adapter = adapter

        viewModel.articleList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}