package com.safire.vitaminsmineralscheatsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safire.vitaminsmineralscheatsheet.model.DataSource
import com.safire.vitaminsmineralscheatsheet.model.VitaminMineralItem
import com.safire.vitaminsmineralscheatsheet.ui.theme.VitaminsMineralsCheatsheetTheme


const val BULLET = "\u2022"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitaminsMineralsCheatsheetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VitaminsMineralsApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun VitaminsMineralsApp(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
    ) {
        items(items = DataSource.data) {
            VitaminMineralCard(it)
        }
    }
}


@Composable
fun VitaminMineralCard(
    vitaminMineralItem: VitaminMineralItem,
    modifier: Modifier = Modifier

) {
    var expanded by remember { mutableStateOf(false) }
    val sources = stringResource(vitaminMineralItem.richSources).split("\n")
    val benefits = stringResource(vitaminMineralItem.benefits).split("\n")

    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(vitaminMineralItem.image),
                    contentDescription = stringResource(R.string.carrot_description),
                    Modifier
                        .clip(CircleShape)
                        .size(100.dp)
                )
                Column(
                ) {
                    Text(
                        text = stringResource(vitaminMineralItem.scientificNames),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = stringResource(vitaminMineralItem.name),
                        style = MaterialTheme.typography.titleMedium,

                        fontSize = 26.sp
                    )
                }

            }
            Text(stringResource(vitaminMineralItem.description))

            Spacer(modifier.size(4.dp))

            if (expanded) {
                Text(
                    "Rich food sources:",
                    fontWeight = FontWeight.SemiBold,

                    )


                val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
                Text(
                    buildAnnotatedString {
                        sources.forEach {
                            withStyle(style = paragraphStyle) {
                                append(BULLET)
                                append("\t\t")
                                append(it)
                            }
                        }
                    }
                )
                Spacer(modifier.size(4.dp))
                Text(
                    buildAnnotatedString {
                        Text("Health Benefits:", fontWeight = FontWeight.SemiBold)
                        benefits.forEach {
                            withStyle(style = paragraphStyle) {
                                append(BULLET)
                                append("\t\t")
                                append(it)
                            }
                        }
                    }
                )
            }

            ExpandCollapseButton(
                onClick = { expanded = !expanded },
                text = if (!expanded) "See more" else "see less",
                icon = if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = if (!expanded) "Arrow down" else "Arrow up",
                modifier = Modifier.fillMaxWidth() //TODO
            )

        }
    }
}

@Composable
fun ExpandCollapseButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    text: String,
    modifier: Modifier = Modifier
) {
    FilledTonalIconButton(
        onClick = onClick,
        modifier = modifier

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),

            ) {
            Icon(imageVector = icon, contentDescription = contentDescription)
            Text(text, fontWeight = FontWeight.SemiBold)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VitaminsMineralsCheatsheetTheme {
        VitaminsMineralsApp()
    }
}