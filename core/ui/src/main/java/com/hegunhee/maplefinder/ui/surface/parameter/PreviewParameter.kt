package com.hegunhee.maplefinder.ui.surface.parameter

import com.hegunhee.maplefinder.designsystem.R
import com.hegunhee.maplefinder.model.Grade
import com.hegunhee.maplefinder.model.World
import com.hegunhee.maplefinder.model.character.Character
import com.hegunhee.maplefinder.model.character.CharacterBasic
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
            level = 0,
            jobName = "엔젤릭버스터",
            jobLevel = "",
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
}
