package com.tecsup.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tecsup.lab13.ui.theme.Lab13Theme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateColorExample()
        }
    }
}

//Ejercicio 1
@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { visible = !visible }) {
            Text(text = if (visible) "Ocultar" else "Mostrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
    }
}

//Ejercicio 2

@Composable
fun AnimateColorExample() {
    var isBlue by remember { mutableStateOf(true) }
    val color by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isBlue = !isBlue }) {
            Text("Cambiar Color")
        }
    }
}

