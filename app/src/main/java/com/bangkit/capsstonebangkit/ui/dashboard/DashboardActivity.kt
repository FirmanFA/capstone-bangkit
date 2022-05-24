package com.bangkit.capsstonebangkit.ui.dashboard

import android.os.Bundle
import androidx.preference.PreferenceManager
import com.bangkit.capsstonebangkit.databinding.ActivityDashboardBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity

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


    }
}