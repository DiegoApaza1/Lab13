package com.tecsup.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tecsup.lab13.ui.theme.Lab13Theme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CombinedAnimationsExample()
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
//Ejercicio 3
@Composable
fun AnimateSizeAndPositionExample() {
    var isMoved by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (isMoved) 150.dp else 100.dp)
    val offset by animateDpAsState(targetValue = if (isMoved) 100.dp else 0.dp)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset, y = offset)
                .background(Color.Magenta)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isMoved = !isMoved }) {
            Text("Mover y Cambiar Tamaño")
        }
    }
}
//Ejercicio 4
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var state by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = state,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
            }
        ) { targetState ->
            when (targetState) {
                0 -> Text("Hola :D", fontSize = 24.sp)
                1 -> Text("Cargando...", fontSize = 24.sp)
                2 -> Text("TECSUP", fontSize = 24.sp)
                3 -> Text("Eliminando...", fontSize = 24.sp)
                4 -> Text("Eliminado correctamente", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { state = 1 }) { Text("Mostrar Contenido") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { state = 3 }) { Text("Eliminar") }
        }
        Row {
            Spacer(modifier = Modifier.heightIn(50.dp))
            Button(onClick = { state = 0 }) { Text("Inicio") }
        }
    }
    LaunchedEffect(key1 = state) {
        if (state == 1) {
            delay(3000) // Simula la carga durante 3 segundos
            state = 2 // Cambia al estado de contenido
        }
        if (state == 3) {
            delay(3000) // Simula la carga durante 3 segundos
            state = 4 // Cambia al estado de contenido
        }
    }
}

//Ejercicio Final

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CombinedAnimationsExample() {
    var isExpanded by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    // Animaciones combinadas
    val size by animateDpAsState(targetValue = if (isExpanded) 200.dp else 100.dp)
    val color by animateColorAsState(
        targetValue = if (isDarkMode) Color.Black else Color.White,
        animationSpec = tween(durationMillis = 1000)
    )
    val offset by animateDpAsState(targetValue = if (!isVisible) 200.dp else 0.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Elemento que cambia de tamaño y color
        Box(
            modifier = Modifier
                .size(size)
                .background(if (isDarkMode) Color.Gray else Color.Cyan)
                .clickable { isExpanded = !isExpanded }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que se desplaza y desaparece
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Button(
                onClick = { isVisible = false },
                modifier = Modifier.offset(y = offset)
            ) {
                Text("Desaparecer")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Transición de contenido para modo claro/oscuro
        AnimatedContent(
            targetState = isDarkMode,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
            }
        ) { darkMode ->
            Text(
                text = if (darkMode) "Modo Oscuro Activado" else "Modo Claro Activado",
                color = if (darkMode) Color.White else Color.Black,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text(if (isDarkMode) "Modo Claro" else "Modo Oscuro")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isVisible = true }) {
                Text("Reaparecer")
            }
        }
    }
}



