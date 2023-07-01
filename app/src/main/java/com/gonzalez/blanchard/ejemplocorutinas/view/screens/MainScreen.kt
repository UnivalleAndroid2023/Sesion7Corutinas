package com.gonzalez.blanchard.ejemplocorutinas.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gonzalez.blanchard.ejemplocorutinas.LoaderDefinido
import com.gonzalez.blanchard.ejemplocorutinas.LoadingBar
import com.gonzalez.blanchard.ejemplocorutinas.esperarSegundos
import com.gonzalez.blanchard.ejemplocorutinas.view.components.BotonAccion
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val context = LocalContext.current

    // Paso 1: Declarar este elemento para poder usar corutinas
    val composableScope = rememberCoroutineScope()

    // Paso 2: declarar una variable que sea mutable para actualizar el progress
    var isLoading by remember { mutableStateOf<Boolean>(false) }

    // Este valor es para actualizar la barra de cargando
    val progressValue = remember { mutableStateOf(0f) } // Valor de progreso definido (0.5f en este caso)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "Ejemplo corutinas",
            )

            // -----------------------------
            // Este es el boton
            // -----------------------------
            BotonAccion(isLoading = isLoading) {
                composableScope.launch {
                    isLoading = true
                    esperarSegundos(context = context) { progress ->
                        // Aquí puedes utilizar el valor de progreso para actualizar tu barra de carga u otro elemento visual
                        // Por ejemplo, puedes almacenar el valor en una variable mutableStateOf y utilizarlo en tu composición
                        progressValue.value = progress
                    }
                    isLoading = false
                }
            }

            // -----------------------------

            if (isLoading) {
                CircularProgressIndicator()
                LoaderDefinido(progress = progressValue.value)
                LoadingBar(isLoading = isLoading)
            }


        }
    }
}

