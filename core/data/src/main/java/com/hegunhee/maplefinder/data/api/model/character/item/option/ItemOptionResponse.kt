package com.hegunhee.maplefinder.data.api.model.character.item.option

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemOptionResponse(
    @SerialName("str")val str: String = "0",
    @SerialName("dex")val dex: String = "0",
    @SerialName("luk")val luk: String = "0",
    @SerialName("int")val int: String = "0",
    @SerialName("all_stat")val allStat: String = "0",
    @SerialName("armor")val armor: String = "0",
    @SerialName("attack_power")val attackPower: String = "0",
    @SerialName("boss_damage")val bossDamage: String = "0",
    @SerialName("damage")val damage: String = "0",
    @SerialName("equipment_level_decrease")val levelDecrease: Int = 0,
    @SerialName("ignore_monster_armor")val ignoreArmor: String = "0",
    @SerialName("magic_power")val magicPower: String = "0",
    @SerialName("max_hp")val maxHp: String = "0",
    @SerialName("max_hp_rate")val maxHpRate: String = "0",
    @SerialName("max_mp")val maxMp: String = "0",
    @SerialName("max_mp_rate")val maxMpRate: String = "0",
    @SerialName("speed")val speed: String = "0",
    @SerialName("jump")val jump: String = "0",
    @SerialName("base_equipment_level") val baseLevel : Int = 0
)

