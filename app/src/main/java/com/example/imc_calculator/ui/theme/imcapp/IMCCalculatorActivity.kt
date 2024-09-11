package com.example.imc_calculator.ui.theme.imcapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc_calculator.ui.theme.IMC_CalculatorTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.shadow
import kotlinx.coroutines.delay

class IMCCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMC_CalculatorTheme {
                Surface(color = Color.White) {
                    IMCCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun IMCCalculatorScreen() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var visible by remember { mutableStateOf(false) }
    var buttonVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300) // Delay before starting animation
        visible = true
        delay(1000) // Delay before showing button
        buttonVisible = true
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AnimatedVisibility(visible = visible) {
                Text(
                    text = "Cálculo de IMC",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF0073B1) // LinkedIn blue
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            AnimatedVisibility(visible = visible) {
                Column {
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Altura (ex: 1.84)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(8.dp)),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("Peso (kg)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(8.dp)),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedVisibility(visible = buttonVisible) {
                Button(
                    onClick = {
                        val heightValue = height.toFloatOrNull()
                        val weightValue = weight.toFloatOrNull()
                        if (heightValue != null && weightValue != null) {
                            val imc = weightValue / (heightValue * heightValue)
                            result = when {
                                imc < 18.5 -> "Abaixo do peso"
                                imc < 24.9 -> "Peso normal"
                                imc < 29.9 -> "Sobrepeso"
                                else -> "Obesidade"
                            }
                            result = "IMC: %.2f - %s".format(imc, result)
                        } else {
                            result = "Por favor, insira valores válidos"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF0073B1)), // LinkedIn blue
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Calcular",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                    )
                }
            }
            result?.let {
                Spacer(modifier = Modifier.height(24.dp))
                AlertDialog(
                    onDismissRequest = { result = null },
                    title = { Text(text = "Resultado", style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF0073B1))) },
                    text = { Text(text = it, style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)) },
                    confirmButton = {
                        Button(
                            onClick = { result = null },
                            colors = ButtonDefaults.buttonColors(Color(0xFF0073B1)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(50.dp),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Text("OK", color = Color.White)
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(8.dp, RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}
