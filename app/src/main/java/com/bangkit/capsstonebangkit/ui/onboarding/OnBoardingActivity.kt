package com.bangkit.capsstonebangkit.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.databinding.ActivityOnBoardingBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.login.LoginActivity

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {

    override fun getViewBinding() = ActivityOnBoardingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .clear()
                .apply()
        }


    }
}