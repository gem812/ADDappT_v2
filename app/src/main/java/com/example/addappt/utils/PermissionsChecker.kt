package com.example.addappt.utils

import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.content.Context


fun checkUsageStatsPermission(context : Context): Boolean {
    var appOpsManager: AppOpsManager? = null
    var mode: Int = 0
    appOpsManager = context!!.getSystemService(Context.APP_OPS_SERVICE) !! as AppOpsManager
    mode = appOpsManager.checkOpNoThrow(
        OPSTR_GET_USAGE_STATS,
        android.os.Process.myUid(),
        context.packageName
    )
    return mode == MODE_ALLOWED
}
