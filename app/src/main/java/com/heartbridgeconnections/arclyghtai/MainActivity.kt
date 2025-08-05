package com.arclyghtai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arclyghtai.ui.theme.ArcLyghtAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArcLyghtAITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArcLyghtMainScreen()
                }
            }
        }
    }
}

@Composable
fun ArcLyghtMainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F9F8)), // Light Teal background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ArcLyght AI",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color(0xFF00796B),
                fontSize = 36.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to your AI Health Companion",
            color = Color.DarkGray,
            fontSize = 18.sp
        )
    }
}
