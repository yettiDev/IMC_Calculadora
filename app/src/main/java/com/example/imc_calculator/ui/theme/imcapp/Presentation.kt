package com.example.imc_calculator.ui.theme.imcapp

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc_calculator.R
import kotlinx.coroutines.delay

@Composable
fun Apresentation(onContinueClicked: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    var buttonVisible by remember { mutableStateOf(false) }

    val offsetAnim by animateDpAsState(
        targetValue = if (visible) 0.dp else 210.dp,
        animationSpec = tween(
            durationMillis = 1600,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(Unit) {
        delay(1000) // Delay before starting animation
        visible = true
        delay(1000) // Delay before showing button
        buttonVisible = true
    }

    Box(
        modifier = Modifier
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.man_lifting),
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .clip(RoundedCornerShape(100.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "A importância de cuidar da sua saúde",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF0073B1)
                        ),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .offset(y = offsetAnim)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Manter um peso saudável é crucial para a prevenção de doenças e para uma vida mais longa e feliz. Nosso aplicativo de cálculo de IMC da YettiCorps ajuda você a monitorar sua saúde de forma prática e eficiente.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        ),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .offset(y = offsetAnim)
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            AnimatedVisibility(visible = buttonVisible) {
                Button(
                    onClick = onContinueClicked,
                    colors = ButtonDefaults.buttonColors(Color(0xFF0073B1)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Vamos lá!",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                    )
                }
            }
        }
    }
}
