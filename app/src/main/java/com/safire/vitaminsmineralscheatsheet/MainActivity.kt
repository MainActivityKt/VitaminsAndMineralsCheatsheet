package com.safire.vitaminsmineralscheatsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
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
import androidx.window.core.layout.WindowWidthSizeClass
import com.safire.vitaminsmineralscheatsheet.model.DataSource
import com.safire.vitaminsmineralscheatsheet.model.MineralItem
import com.safire.vitaminsmineralscheatsheet.model.Solubility
import com.safire.vitaminsmineralscheatsheet.model.VitaminItem
import com.safire.vitaminsmineralscheatsheet.model.MicronutrientItem
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

    val numberOfColumns = when(currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT, WindowWidthSizeClass.MEDIUM -> 1
        else -> 2
    }


    LazyVerticalStaggeredGrid (
        columns = StaggeredGridCells.Fixed(numberOfColumns),
        verticalItemSpacing = dimensionResource(R.dimen.large_padding),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_padding)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.medium_padding)),
        modifier = modifier
            .fillMaxHeight()
    ) {

        items(items = DataSource.data) {
            VitaminMineralCard(it)
        }
    }
}


@Composable
fun VitaminMineralCard(
    vitaminMineralItem: MicronutrientItem,
    modifier: Modifier = Modifier

) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val itemSources = stringResource(vitaminMineralItem.richSources).split("\n")
    val itemBenefits = stringResource(vitaminMineralItem.benefits).split("\n")

    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = dimensionResource(R.dimen.small_padding)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.medium_padding),
                    end = dimensionResource(R.dimen.medium_padding),
                    top = dimensionResource(R.dimen.small_padding),
                    bottom = dimensionResource(R.dimen.small_padding))
                .fillMaxWidth()

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(vitaminMineralItem.image),
                    contentDescription = stringResource(vitaminMineralItem.imageDescription),
                    Modifier
                        .clip(CircleShape)
                        .size(dimensionResource(R.dimen.image_size))
                )
                Column(
                ) {
                    Text(
                        text = stringResource(
                            if (vitaminMineralItem is VitaminItem) {
                                vitaminMineralItem.scientificNames
                            } else {
                                (vitaminMineralItem as MineralItem).chemicalSymbol
                            }
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                    )
                    Text(
                        text = stringResource(vitaminMineralItem.name),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 26.sp
                    )
                    if (vitaminMineralItem is VitaminItem) {
                        VitaminSolubility(vitaminMineralItem.solubility)
                    }
                }

            }
            Text(stringResource(vitaminMineralItem.itemDescription))

            Spacer(modifier.size(dimensionResource(R.dimen.small_padding)))

            if (expanded) {
                AdditionalInfo(itemSources, itemBenefits, modifier)
            }

            ExpandCollapseButton(
                onClick = { expanded = !expanded },
                text = if (!expanded) "See more" else "See less",
                icon =
                    if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = when {
                    expanded -> stringResource(R.string.arrow_up)
                    else -> stringResource(R.string.arrow_down)
                }, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AdditionalInfo(
    sources: List<String>,
    benefits: List<String>,
    modifier: Modifier
) {
    Text("Rich food sources:", fontWeight = FontWeight.SemiBold)

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
    Spacer(modifier.size(dimensionResource(R.dimen.small_padding)))
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

@Composable
private fun VitaminSolubility(solubility: Solubility) {
    OutlinedCard(
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)),
            text = when (solubility) {
                Solubility.WATER_SOLUBLE -> "Water soluble"
                Solubility.FAT_SOLUBLE -> "Fat soluble"
            },
            fontWeight = FontWeight.W500
        )
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
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
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