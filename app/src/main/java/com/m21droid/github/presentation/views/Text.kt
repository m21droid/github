package com.m21droid.github.presentation.views

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val textStyle = TextStyle(
    lineHeight = 1.em,
    platformStyle = PlatformTextStyle(false),
    lineHeightStyle = LineHeightStyle(LineHeightStyle.Alignment.Center, LineHeightStyle.Trim.None)
)

@Preview
@Composable
fun PrimaryTextPreview() {
    PreviewColumn {
        MainText(text = "Main Text")
        WhiteText(text = "White Text")
        PrimaryText(text = "Primary Text")
    }
}

@Composable
fun MainText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        color = Black,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        style = LocalTextStyle.current.merge(textStyle)
    )
}

@Composable
fun WhiteText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
) {
    Text(
        text = text,
        modifier = modifier,
        color = White,
        fontSize = fontSize,
        style = LocalTextStyle.current.merge(textStyle)
    )
}

@Composable
fun PrimaryText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        textAlign = textAlign,
        style = LocalTextStyle.current.merge(textStyle)
    )
}