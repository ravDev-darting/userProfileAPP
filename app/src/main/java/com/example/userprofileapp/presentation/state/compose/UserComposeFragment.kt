package com.example.userprofileapp.presentation.state.compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.userprofileapp.presentation.state.UiState
import com.example.userprofileapp.presentation.state.viewmodel.UserViewModel

class UserComposeFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )

            setContent {
                MaterialTheme {
                    val state by viewModel.state.collectAsState()

                    when (state) {
                        is UiState.Loading -> {
                            Text("Loading...")
                        }

                        is UiState.Success -> {
                            val users = (state as UiState.Success).users

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                users.forEach { user ->
                                    Text(
                                        text = "Name: ${user.name}",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text("Email: ${user.email}")
                                    Text("Phone: ${user.phone}")
                                    Text("Website: ${user.website}")
                                    Text(
                                        text = "Address: ${user.address.street}, ${user.address.city}"
                                    )

                                    Text("────────────")
                                }
                            }
                        }

                        is UiState.Error -> {
                            Text((state as UiState.Error).message)
                        }
                    }
                }
            }
        }
    }
}
