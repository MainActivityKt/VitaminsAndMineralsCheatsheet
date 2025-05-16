package com.safire.vitaminsmineralscheatsheet.model

import com.safire.vitaminsmineralscheatsheet.R

object DataSource {
    val data: List<VitaminMineralItem> = listOf(
        VitaminMineralItem(
            image = R.drawable.carrot,
            name = R.string.vitamin_a_name,
            imageDescription = R.string.carrot_description,
            scientificNames = R.string.vitamin_a_scientific_names,
            description = R.string.vitamin_a_description,
            richSources = R.string.vitamin_a_sources,
            benefits = R.string.vitamin_a_benefits,
        ), VitaminMineralItem(
            image = R.drawable.seeds,
            name = R.string.vitamin_b1_name,
            imageDescription = R.string.seeds_description,
            scientificNames = R.string.vitamin_b1_scientific_name,
            description = R.string.vitamin_b1_description,
            richSources = R.string.vitamin_b1_sources,
            benefits = R.string.vitamin_b1_benefits
        ), VitaminMineralItem(
            image = R.drawable.dairy,
            name = R.string.vitamin_b2_name,
            imageDescription = R.string.dairy_description,
            scientificNames = R.string.vitamin_b2_scientific_name,
            description = R.string.vitamin_b2_description,
            richSources = R.string.vitamin_b2_sources,
            benefits = R.string.vitamin_b2_benefits
        ), VitaminMineralItem(
            image = R.drawable.red_mushrooms,
            name = R.string.vitamin_b3_name,
            imageDescription = R.string.seeds_description,
            scientificNames = R.string.vitamin_b3_scientific_name,
            description = R.string.vitamin_b3_description,
            richSources = R.string.vitamin_b3_sources,
            benefits = R.string.vitamin_b3_benefits
        ), VitaminMineralItem(
            image = R.drawable.banana,
            name = R.string.vitamin_b6_name,
            imageDescription = R.string.banana_description,
            scientificNames = R.string.vitamin_b6_scientific_name,
            description = R.string.vitamin_b6_description,
            richSources = R.string.vitamin_b6_sources,
            benefits = R.string.vitamin_b6_benefits
        )
    )
}