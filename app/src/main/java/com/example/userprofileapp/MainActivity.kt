package com.example.userprofileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userprofileapp.presentation.state.compose.UserComposeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // No XML layout needed if you're only showing the fragment
        // You can use a FrameLayout container dynamically
        val containerId = 12345 // arbitrary ID
        val container = androidx.fragment.app.FragmentContainerView(this).apply {
            id = containerId
            layoutParams = androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(
                androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT,
                androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(container)

        // Load your UserComposeFragment
        supportFragmentManager.beginTransaction()
            .replace(containerId, UserComposeFragment())
            .commit()
    }
}
