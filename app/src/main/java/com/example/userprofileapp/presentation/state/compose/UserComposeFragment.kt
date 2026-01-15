package com.example.userprofileapp.presentation.state.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )

            setContent {
                MaterialTheme {
                    val state by viewModel.state.collectAsState()

                    when (state) {
                        is UiState.Loading -> Text("Loading...")
                        is UiState.Success -> {
                            val user = (state as UiState.Success).user
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Name: ${user.name}", style = MaterialTheme.typography.titleLarge)
                                Text(text = "Username: ${user.username}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Phone: ${user.phone}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Website: ${user.website}", style = MaterialTheme.typography.bodyLarge)
                                Text(
                                    text = "Address: ${user.address.street}, ${user.address.suite}, ${user.address.city}, ${user.address.zipcode}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Geo: lat ${user.address.geo.lat}, lng ${user.address.geo.lng}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        is UiState.Error -> Text((state as UiState.Error).message)
                    }
                }
            }
        }
    }
}
