package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

open class VitaminMineralItem (
    @DrawableRes open val image: Int,
    @StringRes open val imageDescription: Int,
    @StringRes open val name: Int,
    @StringRes open val description: Int,
    @StringRes open val richSources: Int,
    @StringRes open val benefits: Int
)