package com.example.addappt.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.BarDrawer

class BarChartDrawer : BarDrawer {

    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    ) {

        canvas.drawRoundRect(
            barArea.left,
            0f,
            barArea.left + 10f,
            barArea.bottom,
            12f,
            12f,
            barPaint.apply {
                color = Color.LightGray
            }
        )

        canvas.drawRoundRect(
            barArea.left,
            barArea.top,
            barArea.left + 10f,
            barArea.bottom,
            12f,
            12f,
            barPaint.apply {
                color = Color.White
            }
        )
    }
}