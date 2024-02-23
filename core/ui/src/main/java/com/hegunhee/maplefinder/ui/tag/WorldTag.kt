package com.hegunhee.maplefinder.ui.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.model.World

@Composable
fun WorldTag(world : World) {
    Surface(
        shape = RoundedCornerShape(20),
        border = BorderStroke(1.dp,Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(world.name, fontSize = 15.sp)
            Icon(painter = painterResource(id = world.icon),contentDescription = world.name,tint = Color.Unspecified)
        }
    }
}