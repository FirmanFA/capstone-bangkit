package com.bangkit.capsstonebangkit.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.capsstonebangkit.databinding.ActivityDashboardBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
}