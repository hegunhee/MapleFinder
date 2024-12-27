package com.hegunhee.maplefinder.ui.surface.parameter

import com.hegunhee.maplefinder.designsystem.R
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.World
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterBasic
import com.hegunhee.maplefinder.model.character.item.CharacterEquipmentItem
import com.hegunhee.maplefinder.model.character.item.EquipmentItem
import com.hegunhee.maplefinder.model.character.item.Item
import com.hegunhee.maplefinder.model.character.item.ItemOption
import com.hegunhee.maplefinder.model.character.item.Title
import com.hegunhee.maplefinder.model.character.item.upgrade.BaseOption
import com.hegunhee.maplefinder.model.character.item.upgrade.CubeOption
import com.hegunhee.maplefinder.model.character.item.upgrade.ScrollOption
import com.hegunhee.maplefinder.model.character.item.upgrade.StarforceOption
import com.hegunhee.maplefinder.model.character.stat.AbilityInfo
import com.hegunhee.maplefinder.model.character.stat.CharacterAbility
import com.hegunhee.maplefinder.model.character.stat.CharacterHyperStat
import com.hegunhee.maplefinder.model.character.stat.CharacterStat
import com.hegunhee.maplefinder.model.character.stat.DetailStat
import com.hegunhee.maplefinder.model.character.stat.HyperStat

internal object PreviewParameter {

    fun createCharacter(): Character {
        return Character(
            ocid = "",
            basic = createBasic(),
            stat = createStat(),
            hyperStat = createHyperStat(),
            ability = createAbility(),
        )
    }

    fun createBasic(): CharacterBasic {
        return CharacterBasic(
            name = "엔젤릭버스터",
            level = 260,
            jobName = "엔젤릭버스터",
            jobLevel = "6",
            world = World("스카니아", R.drawable.ic_search_24), // World 클래스는 추가 정의 필요
            image = "https://example.com/image.png", // ImageUrl 클래스는 추가 정의 필요
            gender = "여성",
            exp = 0,
            expRate = "0%",
            guildName = null,
            date = "2024-12-25"
        )
    }

    fun createStat(): CharacterStat {
        return CharacterStat(
            jobName = "엔젤릭버스터",
            powerLevel = "0",
            detailStatList = listOf(
                DetailStat("DEX", "90"),
                DetailStat("크리티컬 확률", "9%"),
                DetailStat("크리티컬 데미지", "12%"),
                DetailStat("방어율 무시", "30%"),
                DetailStat("데미지", "33%"),
                DetailStat("보스 몬스터 공격 시 데미지 증가", "43%"),
                DetailStat("공격력/마력", "15"),
                DetailStat("획득 경험치", "3.5%")
            ),
            remainAp = 33,
            date = "2024-12-25"
        )
    }

    fun createHyperStat(): CharacterHyperStat {
        return CharacterHyperStat(
            jobName = "엔젤릭버스터",
            currentPreset = listOf(
                HyperStat("민첩성 90 증가", 3, 7, "DEX"),
                HyperStat("크리티컬 확률 9% 증가", 7, 60, "크리티컬 확률"),
                HyperStat("크리티컬 데미지 12% 증가", 12, 265, "크리티컬 데미지"),
                HyperStat("방어율 무시 30% 증가", 10, 150, "방어율 무시"),
                HyperStat("데미지 33% 증가", 11, 200, "데미지"),
                HyperStat("보스 몬스터 공격 시 데미지 43% 증가", 12, 265, "보스 몬스터 공격 시 데미지 증가"),
                HyperStat("공격력과 마력 15 증가", 5, 25, "공격력/마력"),
                HyperStat("획득 경험치 3.5% 증가", 7, 60, "획득 경험치")
            ),
            hyperStatPresetList = listOf(),
            remainHyperStat = 33,
            currentPresetNum = "1"
        )
    }

    fun createAbility(): CharacterAbility {
        return CharacterAbility(
            abilityGrade = Grade.Legendary,
            abilityInfo = listOf(
                AbilityInfo(Grade.Legendary, "0", "보공20%"),
                AbilityInfo(Grade.Epic, "1", "공격력10"),
                AbilityInfo(Grade.Rare, "2", "마력5")
            ),
            abilityPresetList = listOf(null, null, null),
            presetNo = 0,
            remainFame = 1065,
        )
    }

    fun createEquipmentItem(): CharacterEquipmentItem {
        return CharacterEquipmentItem(
            ocid = "",
            mainStat = "덱스",
            basic = createBasic(),
            equipmentItem = EquipmentItem(
                "엔젤릭버스터",
                "여",
                listOf(createItem()),
                listOf(),
                listOf(),
                createTitle(),
                date = "2024-12-27"
            ),
            name = "엔버는함초롱",
            job = "엔젤릭버스터",
            world = World("스카니아", R.drawable.ic_search_24),
            image = "https://open.api.nexon.com/static/maplestory/character/look/MBLCCFMMKOONIBECPFDAIEEJJCJCHBGBENCBIAJNDLOJAOEBMFJDGLHPFNFHACBIPOCHJLPLMCPDMHAKGLANDHDOBFKJICNNEKDALCFCAMNKOLGPEGDLDNLKDHFBFLCMINOGCICALNPHHIHFAJABPKFNJMNKODGDANDECHEGGLCFGGCGDJHKOMCIPFPFOKKGNCIINEEBBENPHIOCLGGEKMAGKPCAOAOBLFJCPABBJFBDEFPHOMNBBCFKGPJALKMK"
        )
    }

    fun createTitle(): Title {
        return Title(
            "",
            "",
            "",
            "https://open.api.nexon.com/static/maplestory/character/look/MBLCCFMMKOONIBECPFDAIEEJJCJCHBGBENCBIAJNDLOJAOEBMFJDGLHPFNFHACBIPOCHJLPLMCPDMHAKGLANDHDOBFKJICNNEKDALCFCAMNKOLGPEGDLDNLKDHFBFLCMINOGCICALNPHHIHFAJABPKFNJMNKODGDANDECHEGGLCFGGCGDJHKOMCIPFPFOKKGNCIINEEBBENPHIOCLGGEKMAGKPCAOAOBLFJCPABBJFBDEFPHOMNBBCFKGPJALKMK",
            "엔버는함초롱"
        )
    }

    fun createItem(): Item {
        return Item(
            part = "모자",
            slot = "모자",
            description = "",
            itemGender = "",
            icon = "https://open.api.nexon.com/static/maplestory/item/icon/KEPCIPOA",
            name = "하이네스 원더러햇",
            shapeIcon = "https://open.api.nexon.com/static/maplestory/item/icon/KEPCIPOA",
            shapeName = "하이네스 원더러햇",
            scrollOption = ScrollOption(
                upgradeCount = "11",
                recoverableCount = "0",
                upgradableCount = "0",
                goldenHammerFlag = "미적용"
            ),
            cuttableCount = "10",
            starforceCountOption = StarforceOption(
                isStarforceItem = true,
                upgardeCount = "17",
                maxStarCount = 25,
                wonderfulScrollFlag = "미적용",
            ),
            dateExpire = "",
            equipmentLevelIncrease = 0,
            growthExp = 0,
            growthLevel = 0,
            totalOption = listOf(
                ItemOption(key = "str", value = "122"),
                ItemOption(key = "dex", value = "226"),
                ItemOption(key = "int", value = "0"),
                ItemOption(key = "luk", value = "20"),
                ItemOption(key = "maxHp", value = "1385"),
                ItemOption(key = "maxMp", value = "360"),
                ItemOption(key = "attackPower", value = "22"),
                ItemOption(key = "magicPower", value = "19"),
                ItemOption(key = "armor", value = "825"),
                ItemOption(key = "speed", value = "0"),
                ItemOption(key = "jump", value = "0"),
                ItemOption(key = "bossDamage", value = "0"),
                ItemOption(key = "ignoreMonsterArmor", value = "10"),
                ItemOption(key = "allStat", value = "4"),
                ItemOption(key = "damage", value = "0"),
                ItemOption(key = "equipmentLevelDecrease", value = "0"),
                ItemOption(key = "maxHpRate", value = "0"),
                ItemOption(key = "maxMpRate", value = "0")
            ),
            baseOption = BaseOption(
                listOf(
                    ItemOption(key = "str", value = "40"),
                    ItemOption(key = "dex", value = "40"),
                    ItemOption(key = "int", value = "0"),
                    ItemOption(key = "luk", value = "0"),
                    ItemOption(key = "maxHp", value = "360"),
                    ItemOption(key = "maxMp", value = "360"),
                    ItemOption(key = "attackPower", value = "2"),
                    ItemOption(key = "magicPower", value = "0"),
                    ItemOption(key = "armor", value = "300"),
                    ItemOption(key = "speed", value = "0"),
                    ItemOption(key = "jump", value = "0"),
                    ItemOption(key = "bossDamage", value = "0"),
                    ItemOption(key = "ignoreMonsterArmor", value = "10"),
                    ItemOption(key = "allStat", value = "0"),
                    ItemOption(key = "maxHpRate", value = "0"),
                    ItemOption(key = "maxMpRate", value = "0"),
                ),
                baseLevel = 150
            ),
            addOption = listOf(
                ItemOption(key = "str", value = "20"),
                ItemOption(key = "dex", value = "80"),
                ItemOption(key = "int", value = "0"),
                ItemOption(key = "luk", value = "20"),
                ItemOption(key = "maxHp", value = "0"),
                ItemOption(key = "maxMp", value = "0"),
                ItemOption(key = "attackPower", value = "0"),
                ItemOption(key = "magicPower", value = "0"),
                ItemOption(key = "armor", value = "0"),
                ItemOption(key = "speed", value = "0"),
                ItemOption(key = "jump", value = "0"),
                ItemOption(key = "bossDamage", value = "0"),
                ItemOption(key = "damage", value = "0"),
                ItemOption(key = "allStat", value = "4"),
                ItemOption(key = "equipmentLevelDecrease", value = "0")
            ),
            etcOption = listOf(
                ItemOption(key = "str", value = "0"),
                ItemOption(key = "dex", value = "44"),
                ItemOption(key = "int", value = "0"),
                ItemOption(key = "luk", value = "0"),
                ItemOption(key = "maxHp", value = "770"),
                ItemOption(key = "maxMp", value = "0"),
                ItemOption(key = "attackPower", value = "1"),
                ItemOption(key = "magicPower", value = "0"),
                ItemOption(key = "armor", value = "55"),
                ItemOption(key = "speed", value = "0"),
                ItemOption(key = "jump", value = "0")
            ),
            exceptionalOption = listOf(
                ItemOption(key = "str", value = "0"),
                ItemOption(key = "dex", value = "0"),
                ItemOption(key = "int", value = "0"),
                ItemOption(key = "luk", value = "0"),
                ItemOption(key = "maxHp", value = "0"),
                ItemOption(key = "maxMp", value = "0"),
                ItemOption(key = "attackPower", value = "0"),
                ItemOption(key = "magicPower", value = "0")
            ),
            starforceOption = listOf(
                ItemOption(key = "str", value = "62"),
                ItemOption(key = "dex", value = "62"),
                ItemOption(key = "int", value = "0"),
                ItemOption(key = "luk", value = "0"),
                ItemOption(key = "maxHp", value = "255"),
                ItemOption(key = "maxMp", value = "0"),
                ItemOption(key = "attackPower", value = "19"),
                ItemOption(key = "magicPower", value = "19"),
                ItemOption(key = "armor", value = "470"),
                ItemOption(key = "speed", value = "0"),
                ItemOption(key = "jump", value = "0")
            ),
            potentialOption = CubeOption(
                grade = Grade.Epic,
                firstOption = "DEX : +6%",
                secondOption = "최대 MP : +3%",
                thirdOption = "최대 HP : +120"
            ),
            additionalOption = CubeOption(
                grade = Grade.Rare,
                firstOption = "공격력 : +10",
                secondOption = "방어력 : +60",
                thirdOption = "",
            ),
            soulName = "",
            soulOption = "",
            specialRingLevel = 0
        )
    }
}
