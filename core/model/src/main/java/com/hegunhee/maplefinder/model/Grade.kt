package com.hegunhee.maplefinder.model

sealed class Grade(
    val name : String,
    val color : Long
) {

    object Rare : Grade(name = "레어",color = 0xFF80D9F4)

    object Epic : Grade(name = "에픽",color = 0xFFCFA8F4)

    object Unique : Grade(name = "유니크",color = 0xFFF2F680)

    object Legendary : Grade(name = "레전드리",color = 0xFF80F680)

    object Unknown : Grade(name = "알 수 없음",color = 0xFF000000)
}