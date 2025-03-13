package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                MainScreen(
                    onExplicitClick = {
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    },
                    onImplicitClick = {
                        val intent = Intent("com.example.OPEN_SECOND_ACTIVITY")
                        startActivity(intent)
                    },
                    onViewImageClick = {
                        val intent = Intent(this, ImageActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit, onViewImageClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello, my name is Reed!")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onExplicitClick) {
                Text("Start Activity Explicitly")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = onImplicitClick) {
                Text("Start Activity Implicitly")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = onViewImageClick) {
                Text("View Image Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GreetingCardTheme {
        MainScreen({}, {}, {})
    }
}
