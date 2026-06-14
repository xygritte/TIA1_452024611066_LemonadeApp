package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageRes = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textRes = when (step) {
        1 -> "Tap the lemon tree to pick a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Lemon Image",
            modifier = Modifier
                .size(250.dp)
                .clickable {

                    when (step) {
                        1 -> {
                            step = 2
                            squeezeCount = Random.nextInt(2, 5)
                        }
                        2 -> {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                step = 3
                            }
                        }
                        3 -> step = 4
                        4 -> step = 1
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = textRes)
    }
}