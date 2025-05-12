package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import com.safire.vitaminsmineralscheatsheet.R

object DataSource {
    val data: List<VitaminMineralItem> = listOf(
            VitaminMineralItem(
                image = R.drawable.carrot,
                name = R.string.vitamin_a_name,
                imageDescription = R.string.carrot_description,
                scientificNames = R.string.vitamin_a_scientificNames,
                description = R.string.vitamin_a_desctiption,
                richSources = R.string.vitamin_a_sources,
                benefits = R.string.vitamin_a_benefits,
            )
    )
}