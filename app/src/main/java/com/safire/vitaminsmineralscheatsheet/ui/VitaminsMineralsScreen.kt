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
import com.safire.vitaminsmineralscheatsheet.R
import com.safire.vitaminsmineralscheatsheet.model.DataSource
import com.safire.vitaminsmineralscheatsheet.model.MicronutrientItem
import com.safire.vitaminsmineralscheatsheet.model.MineralItem
import com.safire.vitaminsmineralscheatsheet.model.Solubility
import com.safire.vitaminsmineralscheatsheet.model.VitaminItem

val bulletedParagraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

@Composable
fun VitaminsMineralsApp(padding: PaddingValues = PaddingValues(dimensionResource(R.dimen.small_padding))) {


    // Chooses either one or two columns for the screen based on its width
    val numberOfColumns = when (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT, WindowWidthSizeClass.MEDIUM -> 1
        else -> 2
    }

    // Stores the state for solubility dialog being open or closed.
    // When solubility is clicked, its state changes to true and it's displayed on the screen
    val openDialog = rememberSaveable { mutableStateOf(false) }
    if (openDialog.value) {
        SolubilityDialog(onDismiss = { openDialog.value = false })
    }

    // Creates a lazy column of {numberOfColumns} width for displaying the vitamin and mineral items
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(numberOfColumns),
        verticalItemSpacing = dimensionResource(R.dimen.medium_padding),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_padding)),
        contentPadding = padding,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {

        items(items = DataSource.data) {
            VitaminMineralCard(
                vitaminMineralItem = it,
                onSolubilityClick = { openDialog.value = !openDialog.value }
            )
        }
    }
}


@Composable
fun VitaminMineralCard(
    vitaminMineralItem: MicronutrientItem,
    onSolubilityClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    // Stores the state of any vitamin mineral item being expanded or collapsed. Each click on the
    // item changes its state
    var expanded by rememberSaveable { mutableStateOf(false) }
    // Stores the rich resources of a vitamin or mineral in a list of String
    val itemSources = stringResource(vitaminMineralItem.richSources).split("\n")
    // Stores the health benefits of a vitamin or mineral in a list of String
    val itemBenefits = stringResource(vitaminMineralItem.benefits).split("\n")

    // The actual vitamin/mineral item UI
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
                Image( // Displaying the image of that vitamin or mineral
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
                            // Displays the scientific name of a vitamin, or the symbol of an item, based on what it is
                            if (vitaminMineralItem is VitaminItem) {
                                vitaminMineralItem.scientificNames
                            } else {
                                (vitaminMineralItem as MineralItem).chemicalSymbol
                            }
                        ),
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                    )
                    Text( //  Displays the name of given vitamin or mineral
                        text = stringResource(vitaminMineralItem.name),
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 26.sp
                    )
                    // If the given item is a vitamin, display its solubility
                    if (vitaminMineralItem is VitaminItem) {
                        VitaminSolubility(vitaminMineralItem.solubility, onSolubilityClick)
                    }
                }

            }
            // Text description of given vitamin or mineral
            Text(stringResource(vitaminMineralItem.itemDescription))
            // A bit of spacing
            Spacer(modifier.size(dimensionResource(R.dimen.small_padding)))
            // If the item is expanded, displays additional info (health benefits + rich resources)
            if (expanded) {
                AdditionalInfo(itemSources, itemBenefits, modifier)
            }

            // Displays the button to expand or collapse an item. The button text and icon are based
            // on the {expanded} state's value, and clicking always changes state's value to its opposite
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

// Displaying the additional info (expanding the vitamin mineral item)
@Composable
private fun AdditionalInfo(
    sources: List<String>,
    benefits: List<String>,
    modifier: Modifier
) {
    buildAnnotatedString {
        Text(
            "Rich food sources:",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        BulletedListText(sources, Modifier)
    }

    Spacer(modifier.size(dimensionResource(R.dimen.small_padding)))
    Text(
        buildAnnotatedString {
            Text(
                "Health Benefits:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            BulletedListText(benefits, Modifier)
        }
    )
}

// A composable for displaying a list of string items in a bulleted list style
@Composable
fun BulletedListText(items: List<String>, modifier: Modifier = Modifier) {
    Text(buildAnnotatedString {
        items.forEach {
            withStyle(style = bulletedParagraphStyle) {
                append(Typography.bullet)
                append("\t\t")
                append(it)
            }
        }
    }, modifier)
}

// Displaying the solubility of a vitamin item and handling the click event with state-hoisting
@Composable
private fun VitaminSolubility(solubility: Solubility, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .clickable { onClick() }

    ) {
        // Displays the solubility text based on a vitamin item being water-soluble or ast-soluble
        Text(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding)),
            text = when (solubility) {
                Solubility.WATER_SOLUBLE -> stringResource(R.string.water_soluble)
                Solubility.FAT_SOLUBLE -> stringResource(R.string.fat_soluble)
            },
            fontWeight = FontWeight.W500,
        )
    }
}

// Displaying the expand/collapse button and a row of text and icon based on the passed arguments
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

// Top app bar displaying hte app icon with its name
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
        }, navigationIcon = {
            Image(painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
        },
        scrollBehavior = scrollBehavior
    )
}

// Displaying the solubility dialog upon clicking the solubility item, managing the onDismiss
// event with state-hoisting
@Composable
fun SolubilityDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Text(
                buildAnnotatedString {
                    Text(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        text = "Vitamins are grouped into two categories",
                        modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)),
                    )

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    ) {
                        append("-> Fat-soluble vitamins: ")
                    }
                    append(stringResource(R.string.fat_solubility))

                    append("\n\n") // equivalent to a spacer, in this case

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    ) {
                        append("-> Water-soluble vitamins: ")
                    }
                    append(stringResource(R.string.water_solubility))
                },
                modifier = Modifier.padding(dimensionResource(R.dimen.large_padding))
            )

        }
    }
}