package com.example.littlelemon

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Type {
    val title = TextStyle(
        fontSize = 60.sp,
        fontFamily = FontFamily(Font(R.font.markazi_text_medium, FontWeight.Medium)),
        color = LittleLemonColor.yellow,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
    val subTitle = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(R.font.markazi_text_regular, FontWeight.Normal)),
        color = LittleLemonColor.gray
    )
    val desc = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.karla_medium, FontWeight.Medium)),
        color = LittleLemonColor.gray
    )
    val paragraph = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.karla_regular, FontWeight.Normal)),
        color = LittleLemonColor.primary,
    )
    val sectionTitle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.karla_extra_bold, FontWeight.ExtraBold)),
        color = LittleLemonColor.dark,
    )
    val sectionCat = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.karla_extra_bold, FontWeight.ExtraBold)),
    )
    val cardTitle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.karla_bold, FontWeight.Bold)),
        color = LittleLemonColor.dark,
    )
    val price = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.karla_medium, FontWeight.Medium)),
        color = LittleLemonColor.dark,
    )
}