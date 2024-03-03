package com.hegunhee.maplefinder.data.api.model.character.item

import com.hegunhee.maplefinder.data.api.model.character.item.option.ItemOptionResponse
import com.hegunhee.maplefinder.model.ImageUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ItemEquipmentResponse(
    @SerialName("item_equipment_part")val part: String,
    @SerialName("item_equipment_slot")val slot: String,
    @SerialName("item_description")val description: String = "",
    @SerialName("item_gender")val itemGender: String = "",
    @SerialName("item_icon")val icon: ImageUrl,
    @SerialName("item_name")val name: String,
    @SerialName("item_shape_icon")val shapeIcon: ImageUrl,
    @SerialName("item_shape_name")val shapeName: String,
    @SerialName("scroll_resilience_count")val scrollResilienceCount: String,
    @SerialName("scroll_upgrade")val scrollUpgrade: String,
    @SerialName("scroll_upgradeable_count")val scrollUpgradeableCount: String,
    @SerialName("cuttable_count")val cuttableCount: String,
    @SerialName("starforce")val starforce: String,
    @SerialName("starforce_scroll_flag")val starforceScrollFlag: String,
    @SerialName("date_expire")val dateExpire: String = "",
    @SerialName("equipment_level_increase")val equipmentLevelIncrease: Int,
    @SerialName("golden_hammer_flag")val goldenHammerFlag: String,
    @SerialName("growth_exp")val growthExp: Int,
    @SerialName("growth_level")val growthLevel: Int,
    @SerialName("item_add_option")val addOption: ItemOptionResponse,
    @SerialName("item_base_option")val baseOption: ItemOptionResponse,
    @SerialName("item_etc_option")val itemEtcOption: ItemOptionResponse,
    @SerialName("item_exceptional_option")val itemExceptionalOption: ItemOptionResponse,
    @SerialName("item_starforce_option")val itemStarforceOption: ItemOptionResponse,
    @SerialName("item_total_option")val itemTotalOption: ItemOptionResponse,
    @SerialName("potential_option_grade")val potentialOptionGrade: String = "",
    @SerialName("potential_option_1")val potentialOption1: String = "",
    @SerialName("potential_option_2")val potentialOption2: String = "",
    @SerialName("potential_option_3")val potentialOption3: String = "",
    @SerialName("additional_potential_option_grade")val additionalPotentialOptionGrade: String = "",
    @SerialName("additional_potential_option_1")val additionalPotentialOption1: String = "",
    @SerialName("additional_potential_option_2")val additionalPotentialOption2: String = "",
    @SerialName("additional_potential_option_3")val additionalPotentialOption3: String = "",
    @SerialName("soul_name")val soulName: String = "",
    @SerialName("soul_option")val soulOption: String = "",
    @SerialName("special_ring_level")val specialRingLevel: Int,
)