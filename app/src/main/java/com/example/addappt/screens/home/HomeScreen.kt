package com.example.addappt.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addappt.R
import com.example.addappt.data.ScreenTimeCalculator
import com.example.addappt.models.ui.CarouselContentsModel
import com.example.addappt.models.ui.CarouselInsetModel
import com.example.addappt.utils.checkUsageStatsPermission
import com.example.addappt.utils.formatScreenTime
import com.example.addappt.utils.formatScreenTimeForChart
import com.example.addappt.utils.screenTimeColorGenerator
import com.example.addappt.utils.timestampAsRoundedHours

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navToEnableUsageStats: () -> Unit
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home", fontSize = 24.sp) }
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
                        .padding(horizontal = 4.dp)
                ) {
                    Text("Screen Time Dashboard", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    if (checkUsageStatsPermission(context = context)) {
                        Log.d("USAGE_STATS", formatScreenTime(ScreenTimeCalculator(context = context).getScreenTimeForCurrentDay()))
                        val screenTimeCalculator : ScreenTimeCalculator = ScreenTimeCalculator(context = context)

                        var screenTimeForToday = screenTimeCalculator.getScreenTimeForCurrentDay()
                        var screenTimeForTheWeek = screenTimeCalculator.getScreenTimeForPreviousWeek()
                        var screenTimeForTheMonth = screenTimeCalculator.getScreenTimeForPreviousWeek()
                        var averageForTheWeek = screenTimeForTheWeek / 7
                        var averageForTheMonth = screenTimeForTheMonth / 28

                        //TODO : Fix erroneous Month total

                        val carouselInfo = CarouselInsetModel(
                            carouselInfo = arrayListOf(
                                CarouselContentsModel(
                                    title = "Today",
                                    text = formatScreenTime(screenTimeForToday),
                                    color = screenTimeColorGenerator(timestampAsRoundedHours(screenTimeForToday))
                                ),
                                CarouselContentsModel(
                                    title = "Previous Week",
                                    text = formatScreenTime(screenTimeForTheWeek)
                                ),
                                CarouselContentsModel(
                                    title = "Previous Month",
                                    text = formatScreenTime(screenTimeForTheMonth),
                                    color = screenTimeColorGenerator(timestampAsRoundedHours(screenTimeForTheMonth))
                                ),
                                CarouselContentsModel(
                                    title = "Week's Average",
                                    text = formatScreenTime(averageForTheWeek),
                                    color = screenTimeColorGenerator(timestampAsRoundedHours(averageForTheWeek))
                                ),
                                CarouselContentsModel(
                                    title = "Months's Average",
                                    text = formatScreenTime(averageForTheMonth),
                                    color = screenTimeColorGenerator(timestampAsRoundedHours(averageForTheMonth))
                                ),
                            )
                        )
                        
                        OptionsCarousel(model = carouselInfo)

                    } else {
                        Surface(
                            modifier = Modifier
                                .clickable { navToEnableUsageStats.invoke() }
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            color = Color.Red.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "Click to enable usage permissions",
                                modifier = Modifier
                                    .padding(vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Moods Dashboard", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))

                    val carouselInsetInfo = CarouselInsetModel(
                        carouselInfo = arrayListOf(
                            CarouselContentsModel(
                                title = "Mood Monitor",
                                icon = R.drawable.ic_smile_face
                            ),
                            CarouselContentsModel(
                                title = "Sleep Monitor",
                                icon = R.drawable.ic_night
                            ),
                            CarouselContentsModel(
                                title = "Hydration Monitor",
                                icon = R.drawable.ic_water_drop
                            ),
                            CarouselContentsModel(
                                title = "Screentime Monitor",
                                icon = R.drawable.ic_phone_crossed
                            )
                        )
                    )

                    OptionsCarousel(model = carouselInsetInfo)
                }

            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionsCarousel(model: CarouselInsetModel) {
    val pageCount = model.carouselInfo.size
    val pagerState = rememberPagerState(0)

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(180.dp)
    ) {
        HorizontalPager(
            pageCount = pageCount,
            modifier = Modifier,
            state = pagerState,
            key = {
                model.carouselInfo[it].title
            },
        ) { index ->
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(12.dp),
                color = model.carouselInfo[index].color
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    if(model.carouselInfo[index].title.isNotEmpty()){
                        Text(
                            text = model.carouselInfo[index].title,
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (model.carouselInfo[index].icon > 0) {
                        Icon(
                            painter = painterResource(id = model.carouselInfo[index].icon),
                            contentDescription = model.carouselInfo[index].title + " icon",
                            modifier = Modifier
                                .size(96.dp)
                        )
                    }
                    Text(
                        text = model.carouselInfo[index].text,
                        color = Color.DarkGray,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if(model.carouselInfo[index].optionalSecondaryText.isNotEmpty()) {
                        Text(
                            text = model.carouselInfo[index].optionalSecondaryText,
                            color = Color.DarkGray,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.Magenta else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(6.dp)
            )
        }
    }


}