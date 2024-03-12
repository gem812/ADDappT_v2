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
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addappt.R
import com.example.addappt.data.ScreenTimeCalculator
import com.example.addappt.models.ui.CarouselContentsModel
import com.example.addappt.models.ui.CarouselInsetModel
import com.example.addappt.utils.checkUsageStatsPermission
import com.example.addappt.utils.formatScreenTime

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
                        Text("Permissions Enabled")
                        Log.d(
                            "USAGE_STATS",
                            formatScreenTime(ScreenTimeCalculator(context = context).getScreenTimeForCurrentDay())
                        )
                    } else {
                        Surface(
                            modifier = Modifier
                                .clickable { navToEnableUsageStats.invoke() }
                        ) {
                            Text("Click to enable usage permissions")
                        }
                    }

                    val carouselInsetInfo = CarouselInsetModel(
                        carouselInfo = arrayListOf(
                            CarouselContentsModel(
                                title = "Mood Monitor",
                                icon = R.drawable.ic_smile_face,
                                text = "Text"
                            ),
                            CarouselContentsModel(
                                title = "Sleep Monitor",
                                icon = R.drawable.ic_night,
                                text = "Text"
                            ),
                            CarouselContentsModel(
                                title = "Hydration Monitor",
                                icon = R.drawable.ic_water_drop,
                                text = "Text"
                            ),
                            CarouselContentsModel(
                                title = "Screentime Monitor",
                                icon = R.drawable.ic_phone_crossed,
                                text = "Text"
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
                color = Color.LightGray
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = model.carouselInfo[index].icon),
                        contentDescription = model.carouselInfo[index].title + " icon",
                        modifier = Modifier
                            .size(96.dp)
                    )
                    Text(
                        text = model.carouselInfo[index].title,
                        color = Color.DarkGray,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
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