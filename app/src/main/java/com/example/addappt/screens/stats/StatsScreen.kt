package com.example.addappt.screens.stats

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addappt.components.charts.GenerateBarChart
import com.example.addappt.data.ScreenTimeCalculator
import com.example.addappt.utils.checkUsageStatsPermission
import com.example.addappt.utils.convertLongToDay
import com.example.addappt.utils.timestampAsRoundedHours
import com.github.tehras.charts.bar.BarChartData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stats", fontSize = 24.sp) }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                ) {
                    if (checkUsageStatsPermission(context = context)) {
                        Text(
                            text = "Weekly Screen Time",
                            modifier = Modifier
                                .padding(vertical = 6.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(start = 18.dp, top = 6.dp, end = 6.dp, bottom = 12.dp)
                        ) {
                            WeeklyScreenTimeUsageChart(context = context)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WeeklyScreenTimeUsageChart(context: Context) {

    var chartData = mutableListOf<BarChartData.Bar>()
    var weeklyScreenTimeData =
        ScreenTimeCalculator(context = context).getScreenTimeBreakdownForPreviousWeek()

    weeklyScreenTimeData.forEach {

        val color = if (timestampAsRoundedHours(it.totalScreenTime) < 2L) {
            Color.Green.copy(alpha = 0.5f)
        } else if (timestampAsRoundedHours(it.totalScreenTime) in 2L..4L) {
            Color.Yellow.copy(alpha = 0.5f)
        } else {
            Color.Red.copy(alpha = 0.5f)
        }

        chartData.add(
            BarChartData.Bar(
                value = it.totalScreenTime.toFloat(),
                color = color,
                label = convertLongToDay(it.timestamp).substring(0, 1)
            )
        )
    }

    GenerateBarChart(barChartData = chartData)

}