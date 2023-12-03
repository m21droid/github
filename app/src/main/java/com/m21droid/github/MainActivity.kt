package com.m21droid.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.m21droid.github.presentation.navigation.Navigation
import com.m21droid.github.presentation.theme.GithubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubTheme {
                Navigation()
            }
        }
    }

}