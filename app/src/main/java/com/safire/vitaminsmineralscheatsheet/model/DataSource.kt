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
        ), VitaminMineralItem(
            image = R.drawable.egg,
            name = R.string.vitamin_b7_name,
            imageDescription = R.string.egg_description,
            scientificNames = R.string.vitamin_b7_scientific_name,
            description = R.string.vitamin_b7_description,
            richSources = R.string.vitamin_b7_sources,
            benefits = R.string.vitamin_b7_benefits
        ), VitaminMineralItem(
            image = R.drawable.broccoli,
            name = R.string.vitamin_b9_name,
            imageDescription = R.string.broccoli_description,
            scientificNames = R.string.vitamin_b9_scientific_name,
            description = R.string.vitamin_b9_description,
            richSources = R.string.vitamin_b9_sources,
            benefits = R.string.vitamin_b9_benefits
        ), VitaminMineralItem(
            image = R.drawable.meat,
            name = R.string.vitamin_b12_name,
            imageDescription = R.string.meat_description,
            scientificNames = R.string.vitamin_b12_scientific_name,
            description = R.string.vitamin_b12_description,
            richSources = R.string.vitamin_b12_sources,
            benefits = R.string.vitamin_b12_benefits
        ), VitaminMineralItem(
            image = R.drawable.lemons,
            name = R.string.vitamin_c_name,
            imageDescription = R.string.lemons_description,
            scientificNames = R.string.vitamin_c_scientific_name,
            description = R.string.vitamin_c_description,
            richSources = R.string.vitamin_c_sources,
            benefits = R.string.vitamin_c_benefits
        )
    )
}