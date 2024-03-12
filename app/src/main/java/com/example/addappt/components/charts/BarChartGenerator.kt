package com.example.addappt.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addappt.utils.formatScreenTimeForChart
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer

@Composable
fun GenerateBarChart(
    barChartData : List<BarChartData.Bar> = emptyList()
){
    BarChart(
        barChartData = BarChartData(
            bars = barChartData
        ),
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        barDrawer = BarChartDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = Color.LightGray,
            labelValueFormatter = ::formatScreenTimeForChart,
            labelRatio = 7,
            labelTextSize = 12.sp
        ),
        labelDrawer = LabelDrawer()
    )
}