package com.safire.vitaminsmineralscheatsheet.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/** A Micronutrient Item can be either vitamin or mineral.
 * The parameters are all resource values, either string or drawable resources.
 * @param image drawable res value of the image to be displayed
 * @param imageDescription string res of content description for that image
 * @param name string res of the micronutrient's name
 * @param itemDescription string res of the micronutrient's description
 * @param richSources string res of the resources high in the micronutrient
 * @param benefits string res of the health benefits of the micronutrient
 */
open class MicronutrientItem (
    @DrawableRes open val image: Int,
    @StringRes open val imageDescription: Int,
    @StringRes open val name: Int,
    @StringRes open val itemDescription: Int,
    @StringRes open val richSources: Int,
    @StringRes open val benefits: Int
)