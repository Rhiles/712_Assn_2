package com.example.greetingcard

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {

    private val permission = "com.example.greetingcard.MSE412"
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var permissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            permissionGranted = isGranted
            if (!isGranted) {
                Toast.makeText(this, "Permission is required to continue", Toast.LENGTH_SHORT).show()
            }
        }

        // Check permission at runtime
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(permission) // <-- added
        } else {
            permissionGranted = true // <-- added
        }

        setContent {
            GreetingCardTheme {
                MainScreen(
                    onExplicitClick = {
                        if (permissionGranted) {
                            val intent = Intent(this, SecondActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT)
                                .show()
                        }
                        },
                    onImplicitClick = {
                        if (permissionGranted) {
                            val intent = Intent("com.example.OPEN_SECOND_ACTIVITY")
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT)
                                .show()
                        }
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
            Text(text = "Reed Hiles 1357069")
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
