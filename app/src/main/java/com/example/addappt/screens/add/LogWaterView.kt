package com.example.addappt.screens.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.addappt.R

private val waterIconList = listOf(
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup,
    R.drawable.ic_water_cup
)

@Composable
fun WaterGrid() {
    Grid(
        columns = 3
    ) {

        var lastClickedIndex = remember { mutableStateOf(-1) }

        waterIconList.forEachIndexed { index, i ->



            ClickableWaterIcon(
                index = index,
                lastClickedIndex = lastClickedIndex,
                onClick = { indexClicked ->
                lastClickedIndex.value = indexClicked
            })
        }
    }
}

@Composable
fun ClickableWaterIcon(
    index: Int,
    lastClickedIndex : MutableState<Int>,
    onClick: (Int) -> Unit
) {

    var tint = remember { mutableStateOf(Color.Gray) }

    tint.value = if(lastClickedIndex.value < index) Color.Gray else Color.Cyan

    Box() {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clickable {
                    onClick(index)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_water_cup),
                contentDescription = "Water Icon",
                tint = tint.value
            )
        }
    }
}

@Composable
fun Grid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measureables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = itemWidth)
        val colHeights = Array(columns) { 0 }
        val colIndices = Array(measureables.size) { 0 }
        val placeables = measureables.map { it.measure(itemConstraints) }

        placeables.forEachIndexed { index, placeable ->
            val col = colHeights.indexOf(colHeights.minOrNull()!!)
            colIndices[index] = col
            colHeights[col] += placeable.height
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight

        layout(constraints.maxWidth, height) {
            val colY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val col = colIndices[index]
                val x = col * itemWidth
                val y = colY[col]
                placeable.place(x, y)
                colY[col] += placeable.height
            }
        }
    }
}