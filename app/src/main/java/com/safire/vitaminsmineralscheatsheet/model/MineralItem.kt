package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MineralItem(
    @DrawableRes override val image: Int,
    @StringRes override val imageDescription: Int,
    @StringRes override val name: Int,
    @StringRes override val itemDescription: Int,
    @StringRes override val richSources: Int,
    @StringRes override val benefits: Int,
    @StringRes val chemicalSymbol: Int,
): MicronutrientItem(image, imageDescription, name, itemDescription, richSources, benefits)
