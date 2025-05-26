package com.safire.vitaminsmineralscheatsheet.model

import com.safire.vitaminsmineralscheatsheet.R

object DataSource {
    val data: List<VitaminMineralItem> = listOf(
        VitaminItem(
            image = R.drawable.carrot,
            name = R.string.vitamin_a_name,
            imageDescription = R.string.carrot_description,
            scientificNames = R.string.vitamin_a_scientific_names,
            description = R.string.vitamin_a_description,
            richSources = R.string.vitamin_a_sources,
            benefits = R.string.vitamin_a_benefits,
            solubility = Solubility.FAT_SOLUBLE
        ), VitaminItem(
            image = R.drawable.seeds,
            name = R.string.vitamin_b1_name,
            imageDescription = R.string.seeds_description,
            scientificNames = R.string.vitamin_b1_scientific_name,
            description = R.string.vitamin_b1_description,
            richSources = R.string.vitamin_b1_sources,
            benefits = R.string.vitamin_b1_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.dairy,
            name = R.string.vitamin_b2_name,
            imageDescription = R.string.dairy_description,
            scientificNames = R.string.vitamin_b2_scientific_name,
            description = R.string.vitamin_b2_description,
            richSources = R.string.vitamin_b2_sources,
            benefits = R.string.vitamin_b2_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.red_mushrooms,
            name = R.string.vitamin_b3_name,
            imageDescription = R.string.seeds_description,
            scientificNames = R.string.vitamin_b3_scientific_name,
            description = R.string.vitamin_b3_description,
            richSources = R.string.vitamin_b3_sources,
            benefits = R.string.vitamin_b3_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.banana,
            name = R.string.vitamin_b6_name,
            imageDescription = R.string.banana_description,
            scientificNames = R.string.vitamin_b6_scientific_name,
            description = R.string.vitamin_b6_description,
            richSources = R.string.vitamin_b6_sources,
            benefits = R.string.vitamin_b6_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.egg,
            name = R.string.vitamin_b7_name,
            imageDescription = R.string.egg_description,
            scientificNames = R.string.vitamin_b7_scientific_name,
            description = R.string.vitamin_b7_description,
            richSources = R.string.vitamin_b7_sources,
            benefits = R.string.vitamin_b7_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.broccoli,
            name = R.string.vitamin_b9_name,
            imageDescription = R.string.broccoli_description,
            scientificNames = R.string.vitamin_b9_scientific_name,
            description = R.string.vitamin_b9_description,
            richSources = R.string.vitamin_b9_sources,
            benefits = R.string.vitamin_b9_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.meat,
            name = R.string.vitamin_b12_name,
            imageDescription = R.string.meat_description,
            scientificNames = R.string.vitamin_b12_scientific_name,
            description = R.string.vitamin_b12_description,
            richSources = R.string.vitamin_b12_sources,
            benefits = R.string.vitamin_b12_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.lemons,
            name = R.string.vitamin_c_name,
            imageDescription = R.string.lemons_description,
            scientificNames = R.string.vitamin_c_scientific_name,
            description = R.string.vitamin_c_description,
            richSources = R.string.vitamin_c_sources,
            benefits = R.string.vitamin_c_benefits,
            solubility = Solubility.WATER_SOLUBLE
        ), VitaminItem(
            image = R.drawable.sun,
            name = R.string.vitamin_d_name,
            imageDescription = R.string.sun_description,
            scientificNames = R.string.vitamin_d_scientific_name,
            description = R.string.vitamin_d_description,
            richSources = R.string.vitamin_d_sources,
            benefits = R.string.vitamin_d_benefits,
            solubility = Solubility.FAT_SOLUBLE
        ), VitaminItem(
            image = R.drawable.sunflowers,
            name = R.string.vitamin_e_name,
            imageDescription = R.string.sunflowers_description,
            scientificNames = R.string.vitamin_e_scientific_name,
            description = R.string.vitamin_e_description,
            richSources = R.string.vitamin_e_sources,
            benefits = R.string.vitamin_e_benefits,
            solubility = Solubility.FAT_SOLUBLE
        ), VitaminItem(
            image = R.drawable.kale,
            name = R.string.vitamin_k_name,
            imageDescription = R.string.kale_description,
            scientificNames = R.string.vitamin_k_scientific_name,
            description = R.string.vitamin_k_description,
            richSources = R.string.vitamin_k_sources,
            benefits = R.string.vitamin_k_benefits,
            solubility = Solubility.FAT_SOLUBLE
        ), MineralItem (
            image = R.drawable.cheese,
            name = R.string.calcium_name,
            imageDescription = R.string.cheese_description,
            chemicalSymbol = R.string.calcium_symbol,
            description = R.string.calcium_description,
            richSources = R.string.calcium_sources,
            benefits = R.string.calcium_benefits,
        ), MineralItem (
            image = R.drawable.salt_shaker,
            name = R.string.iodine_name,
            imageDescription = R.string.salt_shaker_description,
            chemicalSymbol = R.string.iodine_symbol,
            description = R.string.iodine_description,
            richSources = R.string.iodine_sources,
            benefits = R.string.iodine_benefits,
        ), MineralItem (
            image = R.drawable.beans,
            name = R.string.iron_name,
            imageDescription = R.string.beans_description,
            chemicalSymbol = R.string.iron_symbol,
            description = R.string.iron_description,
            richSources = R.string.iron_sources,
            benefits = R.string.iron_benefits,
        ), MineralItem (
            image = R.drawable.spinach,
            name = R.string.magnesium_name,
            imageDescription = R.string.spinach_description,
            chemicalSymbol = R.string.magnesium_symbol,
            description = R.string.magnesium_description,
            richSources = R.string.magnesium_sources,
            benefits = R.string.magnesium_benefits,
        ), MineralItem (
            image = R.drawable.tomato,
            name = R.string.potassium_name,
            imageDescription = R.string.tomato_description,
            chemicalSymbol = R.string.potassium_symbol,
            description = R.string.potassium_description,
            richSources = R.string.potassium_sources,
            benefits = R.string.potassium_benefits,
        ), MineralItem (
            image = R.drawable.meat,
            name = R.string.zinc_name,
            imageDescription = R.string.meat_description,
            chemicalSymbol = R.string.zinc_symbol,
            description = R.string.zinc_description,
            richSources = R.string.zinc_sources,
            benefits = R.string.zinc_benefits,
        )
    )
}