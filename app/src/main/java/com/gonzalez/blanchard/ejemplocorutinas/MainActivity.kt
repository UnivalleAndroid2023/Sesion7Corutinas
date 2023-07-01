package com.gonzalez.blanchard.ejemplocorutinas

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gonzalez.blanchard.ejemplocorutinas.ui.theme.EjemploCorutinasTheme
import com.gonzalez.blanchard.ejemplocorutinas.view.screens.MainScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploCorutinasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun LoadingBar(isLoading: Boolean) {
    if (isLoading) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp))
    }
}


@Composable
fun LoaderDefinido(progress:Float) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}

// La funcion debe declararse suspend
suspend fun esperarSegundos(context: Context, progress: (Float) -> Unit) {
    val totalDelay = 6000L // Duración total de la acción en milisegundos
    val stepDelay = totalDelay / 4 // Duración de cada etapa en milisegundos

    delay(stepDelay)
    withContext(Dispatchers.Main) { progress(0.25f) } // Actualizar el progreso al 25%
    delay(stepDelay)
    withContext(Dispatchers.Main) { progress(0.50f) } // Actualizar el progreso al 50%
    delay(stepDelay)
    withContext(Dispatchers.Main) { progress(0.75f) } // Actualizar el progreso al 75%
    delay(stepDelay)
    withContext(Dispatchers.Main) { progress(1.0f) } // Actualizar el progreso al 100%


    withContext(Dispatchers.Main) {
        Toast.makeText(context, "Acción completada", Toast.LENGTH_SHORT).show()
    }
}









@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploCorutinasTheme {
        MainScreen()
    }
}