package com.example.myapplication.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            MainContent(modifier = Modifier.padding(innerPadding))
        },
        bottomBar = {
            BottomBar()
        }
    )
}

@Composable
fun TopBar() {
    Text(text = "Top Bar")
}

@Composable
fun MainContent(modifier: Modifier) {
    Text(text = "Main Content", modifier = modifier)
}

@Composable
fun BottomBar() {
    Text(text = "Bottom Bar")
}
