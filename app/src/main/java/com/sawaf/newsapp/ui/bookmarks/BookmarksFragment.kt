package com.sawaf.newsapp.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawaf.newsapp.R
import com.sawaf.newsapp.databinding.BookmarksFragBinding
import com.sawaf.newsapp.ui.home.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private var _binding: BookmarksFragBinding? = null
    private val viewModel by viewModels<BookmarksViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarksFragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ArticlesAdapter(viewModel::bookmarkArticle) { item, _ ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_detailsFragment)
        }
        binding.articleRv.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookmarksList.collect {
                    adapter.submitList(it)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}