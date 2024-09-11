package com.example.imc_calculator.ui.theme.imcapp

import android.app.Presentation
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imc_calculator.R
import com.example.imc_calculator.ui.theme.IMC_CalculatorTheme

import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMC_CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   /* AppPresentationScreen{
                        startActivity(Intent(this,IMCCalculatorActivity::class.java))
                    }*/

                    AppNavHost()



                }
            }
        }
    }
}








@Composable


fun AppNavHost() {


    val navController = rememberNavController()
    NavHost(navController, startDestination = "app_presentation") {
        composable("app_presentation") {
            AppPresentationScreen { navController.navigate("apresentation") }
        }

        composable("apresentation") {
            Apresentation { navController.navigate("imc_calculator") }
        }

        composable("imc_calculator") {
            IMCCalculatorScreen()
        }
    }
}


@Composable
fun AppPresentationScreen(onNextClick: () -> Unit) {
    val URL = "https://www.linkedin.com/in/diogo-muniz-944384228/"
    val context = LocalContext.current
    var visible by remember { mutableStateOf(false) }
    var buttonVisible by remember { mutableStateOf(false) }
    var iconsVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300) // Delay before starting animation
        visible = true
        delay(1000) // Delay before showing button
        buttonVisible = true
        delay(1000)
        iconsVisible = true
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
                        painter = painterResource(id = R.drawable.yetticon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(75.dp))
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Bem-vindo ao aplicativo de cálculo de IMC da YettiCorps!",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            AnimatedVisibility(visible = buttonVisible) {
                Button(
                    onClick = onNextClick,
                    colors = ButtonDefaults.buttonColors(Color(0xFF0073B1)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Começar",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                    )
                }
            }
        }

        AnimatedVisibility(visible = iconsVisible) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.likedin_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(URL)
                                }
                                context.startActivity(intent)
                                Toast
                                    .makeText(context, "Abrindo LinkedIn", Toast.LENGTH_LONG)
                                    .show()
                            }
                    )

                    Text(
                        text = "YettiCorps CEO's",
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
