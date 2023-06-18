package com.sawaf.newsapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sawaf.newsapp.R
import com.sawaf.newsapp.core.utils.observe
import com.sawaf.newsapp.core.utils.toast
import com.sawaf.newsapp.databinding.FragmentDetailsBinding
import com.sawaf.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel by viewModels<DetailsViewModel>()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.articleItem) { item ->
            binding.apply {
                articleContent.text = item.description
                if (item.isBookmarked) {
                    binding.saveBtn.setImageResource(android.R.drawable.btn_star_big_on)
                } else {
                    binding.saveBtn.setImageResource(android.R.drawable.btn_star_big_off)
                }
                saveBtn.setOnClickListener{
                    viewModel.saveArticle(item)
                }
            }
            toast(item.title)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}