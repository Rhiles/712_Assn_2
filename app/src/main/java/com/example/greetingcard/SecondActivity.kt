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

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                ChallengesScreen(onBackClick = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun ChallengesScreen(onBackClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Mobile Software Engineering Challenges:")
            Spacer(modifier = Modifier.height(10.dp))
            val challenges = listOf(
                "1. Device Fragmentation",
                "2. Battery Consumption Optimization",
                "3. Security Concerns",
                "4. Performance Optimization",
                "5. Network Connectivity Issues"
            )
            challenges.forEach { Text(text = it) }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onBackClick) {
                Text("Main Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengesScreenPreview() {
    GreetingCardTheme {
        ChallengesScreen {}
    }
}
