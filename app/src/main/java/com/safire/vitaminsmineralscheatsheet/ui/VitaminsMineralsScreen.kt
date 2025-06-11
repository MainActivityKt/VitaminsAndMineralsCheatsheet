package com.safire.vitaminsmineralscheatsheet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.window.core.layout.WindowWidthSizeClass
import com.safire.vitaminsmineralscheatsheet.BULLET
import com.safire.vitaminsmineralscheatsheet.R
import com.safire.vitaminsmineralscheatsheet.model.DataSource
import com.safire.vitaminsmineralscheatsheet.model.MicronutrientItem
import com.safire.vitaminsmineralscheatsheet.model.MineralItem
import com.safire.vitaminsmineralscheatsheet.model.Solubility
import com.safire.vitaminsmineralscheatsheet.model.VitaminItem

@Composable
fun VitaminsMineralsApp(padding: PaddingValues = PaddingValues(dimensionResource(R.dimen.small_padding))) {


    //Chooses either one or two columns for the screen based on its width
    val numberOfColumns = when(currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT, WindowWidthSizeClass.MEDIUM -> 1
        else -> 2
    }

    //
    val openDialog = rememberSaveable { mutableStateOf(false) }

    if (openDialog.value) {
        SolubilityDialog(onDismiss = { openDialog.value = false })
    }


    LazyVerticalStaggeredGrid (
        columns = StaggeredGridCells.Fixed(numberOfColumns),
        verticalItemSpacing = dimensionResource(R.dimen.medium_padding),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_padding)),
        contentPadding = padding,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {

        items(items = DataSource.data) {
            VitaminMineralCard(it, onSolubilityClick = { openDialog.value = !openDialog.value })
        }
    }
}


@Composable
fun VitaminMineralCard(
    vitaminMineralItem: MicronutrientItem,
    onSolubilityClick: () -> Unit,
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
                    bottom = dimensionResource(R.dimen.small_padding)
                )
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
                        VitaminSolubility(vitaminMineralItem.solubility, onSolubilityClick)
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
private fun VitaminSolubility(solubility: Solubility, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)),
            text = when (solubility) {
                Solubility.WATER_SOLUBLE -> "Water soluble"
                Solubility.FAT_SOLUBLE -> "Fat soluble"
            },
            fontWeight = FontWeight.W500,

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ), title = {
            Text(
                "Vitamins & Minerals",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 28.sp
            )

        },

        navigationIcon = {
            Image(painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun SolubilityDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog (
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card (
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = ParagraphStyle(
                            textAlign = TextAlign.Center,)) {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)) {
                            append("Vitamins are grouped into two categories\n")
                        }
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("-> Fat-soluble vitamins: ")
                    }
                    append("are stored in the body's liver, fatty tissue, and muscles. The four" +
                            " fat-soluble vitamins are vitamins A, D, E, and K. These vitamins are " +
                            "absorbed more easily by the body in the presence of dietary fat.\n\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("-> Water-soluble vitamins: ")
                    }
                    append("are not stored in the body. The nine water-soluble vitamins are" +
                            " vitamin C and all the B vitamins. Any leftover or excess amounts of " +
                            "these leave the body through the urine. They have to be consumed on a " +
                            "regular basis to prevent shortages or deficiencies in the body. " +
                            "The exception to this is vitamin B12, which can be stored in the " +
                            "liver for many years.")
                },
                modifier = Modifier.padding(dimensionResource(R.dimen.large_padding))

            )
        }
    }
}