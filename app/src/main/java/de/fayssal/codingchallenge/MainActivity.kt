package de.fayssal.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import de.fayssal.codingchallenge.home.HomeScreen
import de.fayssal.codingchallenge.home.HomeViewModel
import de.fayssal.codingchallenge.ui.theme.CodingChallengeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    HomeScreen(viewModel)
                }
            }
        }
    }
}
