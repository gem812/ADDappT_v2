package com.example.addappt.components.charts

import android.graphics.Paint
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.piechart.utils.toLegacyInt

class LabelDrawer() : LabelDrawer {

    private val paint = Paint().apply {
        this.textAlign = Paint.Align.CENTER
        this.color = Color.DarkGray.toLegacyInt()
    }
    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {
        canvas.nativeCanvas.drawText(
            label,
            18f,
            18f,
            paint
        )
    }
}