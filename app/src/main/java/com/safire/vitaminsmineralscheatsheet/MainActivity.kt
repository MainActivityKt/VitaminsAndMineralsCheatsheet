package com.safire.vitaminsmineralscheatsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safire.vitaminsmineralscheatsheet.ui.theme.VitaminsMineralsCheatsheetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitaminsMineralsCheatsheetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VitaminMineralItem(
                        scientificName = "Ascorbic Acid",
                        name = "Vitamin C",
                        description = "Something",
                        image = R.drawable.ic_launcher_foreground,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun VitaminMineralItem(
    scientificName: String,
    name: String,
    description: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier

) {
    ElevatedCard (
        shape = CardDefaults.elevatedShape,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ){
            Text(
                text = scientificName,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth() // make text center vertical
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 10.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VitaminsMineralsCheatsheetTheme {
        Box (modifier = Modifier.fillMaxSize()) {
            VitaminMineralItem(
                scientificName = "Ascorbic Acid",
                name = "Vitamin C",
                description = "Boosts immunity",
                image = R.drawable.ic_launcher_foreground
            )
        }
    }
}