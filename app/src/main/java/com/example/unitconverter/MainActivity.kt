package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitconverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitconverterTheme {
               Surface (
                   modifier = Modifier.fillMaxSize(),
                   color= MaterialTheme.colorScheme.background
               ){
                  // Greeting(name = "Android")
                   UnitConverter()
               }
            }
        }
    }
}


@Composable
fun UnitConverter(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(Color(9046), Color(0xFFD64D4D))
            )
        )) {

    }
   var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meters")}
    var outputUnity by remember{ mutableStateOf("Meters")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    val conversionfactor = remember {mutableStateOf(1.00) }
    val oConversionfactor = remember {mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble=inputValue.toDoubleOrNull() ?: 0.0
        val result=(inputValueDouble * conversionfactor.value*100.0/oConversionfactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //here all the ui element will be stacked each other
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue=it
            convertUnits()
        }           ,
            label={ Text("enter Value")})

        Spacer(modifier = Modifier.height(12.dp))
       // Spacer(modifier = Modifier.padding(12.dp,10.dp))
        Row {
            //input box
            //here all the ui element will be each other
            Box{
                Button(onClick = { iExpanded=true}) {
                    Text(text=inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow down")
                  // Spacer(modifier = Modifier.width(4.dp))
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeters"
                            conversionfactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {   iExpanded=false
                            inputUnit="Meters"
                            conversionfactor.value=1.0
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {   iExpanded=false
                            inputUnit="Feet"
                            conversionfactor.value=0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {   iExpanded=false
                            inputUnit="Milineters"
                            conversionfactor.value=0.001
                            convertUnits() }
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            //output box

            Box{
                //output button
                Button(onClick = {oExpanded=true}) {
                    Text(text=outputUnity)
                 Icon(Icons.Default.ArrowDropDown,
                     contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnity="Centimeters"
                            oConversionfactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = { oExpanded=false
                           outputUnity="meters"
                            oConversionfactor.value=1.00
                            convertUnits()  }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { oExpanded=false
                            outputUnity="Feet"
                            oConversionfactor.value=0.30408
                            convertUnits()  }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = { oExpanded=false
                            outputUnity="Milimeters"
                            oConversionfactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Result",
            fontWeight = FontWeight.Bold,
            color =Color.White )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text=outputValue, fontWeight = FontWeight.ExtraBold, color = Color.White)
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}


