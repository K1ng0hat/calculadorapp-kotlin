package com.example.calculadorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorapp.ui.theme.CalculadorappTheme
//Realizado Por: Adrian Molano

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            suma()
        }
    }
}

@Composable
fun suma() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Calculadora", fontSize = 50.sp)

        TextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text(text = "Ingrese el numero 1") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text(text = "Ingrese el segundo numero") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
//IMPORTANTE: AGREGUE ToDoubleOrNUll para devolver un string sea 'abc' como null y que solo acepte valores double

        // Botón para sumar

        Button(onClick = {
            val num1 = numero1.toDoubleOrNull()
            val num2 = numero2.toDoubleOrNull()
            if (num1 != null && num2 != null) {
                resultado = num1 + num2
            }
        }) {
            Text(text = "Sumar")
        }

        // Botón para restar

        Button(onClick = {
            val num1 = numero1.toDoubleOrNull()
            val num2 = numero2.toDoubleOrNull()
            if (num1 != null && num2 != null) {
                resultado = num1 - num2
            }
        }) {
            Text(text = "Restar")
        }

        // Botón para multiplicar

        Button(onClick = {
            val num1 = numero1.toDoubleOrNull()
            val num2 = numero2.toDoubleOrNull()
            if (num1 != null && num2 != null) {
                resultado = num1 * num2
            }
        }) {
            Text(text = "Multiplicar")
        }

        // Botón para dividir

        Button(onClick = {
            val num1 = numero1.toDoubleOrNull()
            val num2 = numero2.toDoubleOrNull()

            // Comprobacion si ambos números son 0
            if (num1 == 0.0 && num2 == 0.0) {
                resultado = Double.NaN
            } else if (num1 != null && num2 != null && num2 != 0.0) {
                resultado = num1 / num2
            } else {
                resultado = null
            }
        }) {
            Text(text = "Dividir") // Agregue el resultado de que si la division es 0 sea SyntaxError
        }

// INPUT FINAL EN EL RESULTADO DE ERROR
        Text(
            text = when {
                resultado?.isNaN() == true -> "Syntax Error" // Mostrar "Syntax Error" si es NaN From Boton de dividir
                resultado != null -> "El resultado es: $resultado"
                else -> ""
            },
            fontSize = 20.sp
        )


    }
}
