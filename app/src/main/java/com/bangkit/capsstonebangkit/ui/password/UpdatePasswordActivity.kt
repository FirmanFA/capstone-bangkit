package com.bangkit.capsstonebangkit.ui.password

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.capsstonebangkit.databinding.ActivityUpdatePasswordBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity

class UpdatePasswordActivity : BaseActivity<ActivityUpdatePasswordBinding>() {

    override fun getViewBinding() = ActivityUpdatePasswordBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent?.data


        val url = data.toString()

        //get token via dropping first 43 character (http://localhost:4000/reset-password?token=)
        val token = url.drop(43)
        //get token via split string
        val token2 = url.split("token=")[1]
        binding.tvLink.text = token




    }
}