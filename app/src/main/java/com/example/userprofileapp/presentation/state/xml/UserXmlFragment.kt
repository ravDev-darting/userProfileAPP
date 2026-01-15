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
import kotlinx.coroutines.flow.collect

class UserXmlFragment : Fragment(R.layout.fragment_user_xml) {

    private val viewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentUserXmlBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserXmlBinding.bind(view)

        // Observe StateFlow from ViewModel
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loading -> binding.textView.text = "Loading..."
                    is UiState.Success -> binding.textView.text = state.user.name
                    is UiState.Error -> binding.textView.text = state.message
                }
            }
        }

        // Navigate to Compose Fragment
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_xml_to_compose)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
