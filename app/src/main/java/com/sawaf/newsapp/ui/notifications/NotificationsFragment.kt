package com.sawaf.newsapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sawaf.newsapp.core.utils.writeString
import com.sawaf.newsapp.databinding.FragmentNotificationsBinding
import kotlinx.coroutines.launch

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            usBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    requireContext().writeString("apiKey", "us")
                }
            }
            saBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    requireContext().writeString("apiKey", "eg")
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