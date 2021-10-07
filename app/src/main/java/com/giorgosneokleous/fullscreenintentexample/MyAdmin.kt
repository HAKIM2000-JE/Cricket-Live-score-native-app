package com.giorgosneokleous.fullscreenintentexample

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast





/**
 * Created by ssaurel on 04/09/2017.
 */
class MyAdmin : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        Toast.makeText(context, "Device Admin : enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        Toast.makeText(context, "Device Admin : disabled", Toast.LENGTH_SHORT).show()
    }
}
