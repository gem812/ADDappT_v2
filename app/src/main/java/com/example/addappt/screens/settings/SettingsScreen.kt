package com.example.addappt.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.addappt.R
import com.example.addappt.components.widgets.ClickableRowSettings
import com.example.addappt.components.widgets.SearchTextField
import com.example.addappt.navigation.AddapptScreens
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController : NavController,
    navToUsagePermissions : () -> Unit
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Arrow back",
                            modifier = Modifier
                                .clickable { navController.popBackStack() })
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Text("Settings and Privacy", fontSize = 24.sp)
                    }

                }
            )
        },
        content = { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ){
                SearchTextField()

                Text("Your Account", modifier = Modifier.padding(horizontal = 16.dp))

                ClickableRowSettings(
                    icon = R.drawable.ic_accounts,
                    primaryText = "Accounts Centre",
                    subText = "Password, security etc.",
                    destinationRoute = AddapptScreens.ProfileScreen.name,
                    onClick = {navRoute ->
                        navController.navigate(navRoute)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("How to use ADDapt", modifier = Modifier.padding(horizontal = 16.dp))

                ClickableRowSettings(
                    icon = R.drawable.ic_notification,
                    primaryText = "Notifications",
                    destinationRoute = "",
                    onClick = {}
                )
                ClickableRowSettings(
                    icon = R.drawable.ic_time_spent,
                    primaryText = "Screentime Permissions",
                    destinationRoute = "",
                    onClick = {
                        navToUsagePermissions.invoke()
                    }
                )
                Divider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 2.dp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Who can see your account", modifier = Modifier.padding(horizontal = 16.dp))

                ClickableRowSettings(
                    icon = R.drawable.ic_account_privacy,
                    primaryText = "Account Privacy",
                    destinationRoute = "",
                    onClick = {}

                )

                Divider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 2.dp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("More info and support", modifier = Modifier.padding(horizontal = 16.dp))

                ClickableRowSettings(
                    icon = R.drawable.ic_help,
                    primaryText = "Help",
                    destinationRoute = "",
                    onClick = {}

                )
                ClickableRowSettings(
                    icon = R.drawable.ic_about,
                    primaryText = "About",
                    destinationRoute = "",
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 2.dp
                )
                Text("Login", modifier = Modifier.padding(horizontal = 16.dp))

                ClickableRowSettings(
                    icon = R.drawable.ic_logout,
                    primaryText = "Log out user",
                    destinationRoute = "",
                    onClick = {
                        FirebaseAuth.getInstance().signOut().run {
                            navController.navigate(AddapptScreens.LoginScreen.name)
                        }
                    }
                )

            }
        }
    )
}