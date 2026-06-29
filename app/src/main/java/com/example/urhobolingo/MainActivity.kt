package com.example.urhobolingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urhobolingo.ui.UrhoboLingoApp
import com.example.urhobolingo.ui.theme.UrhoboLingoTheme
import com.example.urhobolingo.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrhoboLingoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: AppViewModel = viewModel()
                    UrhoboLingoApp(viewModel)
                }
            }
        }
    }
}
