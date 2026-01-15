package com.example.userprofileapp.presentation.state.xml

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.userprofileapp.R
import com.example.userprofileapp.databinding.FragmentUserXmlBinding
import com.example.userprofileapp.presentation.state.UiState
import com.example.userprofileapp.presentation.state.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collectLatest

class UserXmlFragment : Fragment(R.layout.fragment_user_xml) {

    private val viewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentUserXmlBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserXmlBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.textView.text = "Loading..."
                    }

                    is UiState.Success -> {
                        // Just show first user for XML demo
                        val firstUser = state.users.firstOrNull()
                        binding.textView.text =
                            firstUser?.name ?: "No users found"
                    }

                    is UiState.Error -> {
                        binding.textView.text = state.message
                    }
                }
            }
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_xml_to_compose)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
