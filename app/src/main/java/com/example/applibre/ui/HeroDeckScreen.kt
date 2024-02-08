package com.example.applibre.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun ResultScreen(photos: String) {
    Box{
        Text(text = photos)
    }
}