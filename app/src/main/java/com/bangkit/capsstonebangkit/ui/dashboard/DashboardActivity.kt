package com.bangkit.capsstonebangkit.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.bangkit.capsstonebangkit.databinding.ActivityDashboardBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.camera.CameraActivity

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.button.setOnClickListener {
            PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .clear()
                .apply()

            finish()
        }

        binding.btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }


    }
}