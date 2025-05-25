package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class VitaminItem(
    @DrawableRes override val image: Int,
    @StringRes override val imageDescription: Int,
    @StringRes override val name: Int,
    @StringRes override val description: Int,
    @StringRes override val richSources: Int,
    @StringRes override val benefits: Int,
    @StringRes val scientificNames: Int,
    val solubility: Solubility
): VitaminMineralItem(image, imageDescription, name, description, richSources, benefits)