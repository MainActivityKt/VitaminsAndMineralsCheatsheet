package com.safire.vitaminsmineralscheatsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.safire.vitaminsmineralscheatsheet.ui.TopBar
import com.safire.vitaminsmineralscheatsheet.ui.VitaminsMineralsApp
import com.safire.vitaminsmineralscheatsheet.ui.theme.VitaminsMineralsCheatsheetTheme


const val BULLET = "\u2022"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitaminsMineralsCheatsheetTheme {
                Scaffold(
                    topBar = { TopBar(Modifier) }
                ) { innerPadding ->
                    VitaminsMineralsApp(innerPadding)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    VitaminsMineralsCheatsheetTheme {
        VitaminsMineralsApp()
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    VitaminsMineralsCheatsheetTheme {
        TopBar()
    }
}