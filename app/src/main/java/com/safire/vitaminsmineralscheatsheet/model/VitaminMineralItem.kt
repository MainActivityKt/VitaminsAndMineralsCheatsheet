package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class VitaminMineralItem (
    @DrawableRes val image: Int,
    @StringRes val imageDescription: Int,
    @StringRes val name: Int,
    @StringRes val scientificNames: Int,
    @StringRes val description: Int,
    @StringRes val richSources: Int,
    @StringRes val benefits: Int
)