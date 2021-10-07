
package com.giorgosneokleous.fullscreenintentexample

import android.app.Activity
import android.app.ActivityManager
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var lock: Button? = null
    private var disable: Button? = null
    private var enable: Button? = null
    private var devicePolicyManager: DevicePolicyManager? = null
    private var activityManager: ActivityManager? = null
    private var compName: ComponentName? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        compName = ComponentName(this, MyAdmin::class.java)
        lock = findViewById<View>(R.id.lock) as Button
        enable = findViewById<View>(R.id.enableBtn) as Button
        disable = findViewById<View>(R.id.disableBtn) as Button
        lock!!.setOnClickListener(this)
        enable!!.setOnClickListener(this)
        disable!!.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val isActive = devicePolicyManager!!.isAdminActive(compName!!)
        disable!!.visibility = if (isActive) View.VISIBLE else View.GONE
        enable!!.visibility = if (isActive) View.GONE else View.VISIBLE
    }

    override fun onClick(view: View) {
        if (view === lock) {
            val active = devicePolicyManager!!.isAdminActive(compName!!)
            if (active) {
                val intent = Intent(this, FullScreenActivity::class.java)
                startActivity(intent)
                devicePolicyManager!!.lockNow()


            } else {
                Toast.makeText(
                    this,
                    "You need to enable the Admin Device Features",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (view === enable) {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
            intent.putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Additional text explaining why we need this permission"
            )
            startActivityForResult(intent, RESULT_ENABLE)
        } else if (view === disable) {
            devicePolicyManager!!.removeActiveAdmin(compName!!)
            disable!!.visibility = View.GONE
            enable!!.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RESULT_ENABLE -> if (resultCode == RESULT_OK) {
                Toast.makeText(
                    this@MainActivity,
                    "You have enabled the Admin Device features",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Problem to enable the Admin Device features",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val RESULT_ENABLE = 11
    }
}




