package com.bangkit.capsstonebangkit.ui.community.create

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import com.bangkit.capsstonebangkit.databinding.ActivityCreateCommunityBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bumptech.glide.Glide

class CreateCommunityActivity : BaseActivity<ActivityCreateCommunityBinding>() {

    override fun getViewBinding() = ActivityCreateCommunityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val image = Glide.with(this).load("oke").submit()

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("code", "123456")
        clipboardManager.setPrimaryClip(clipData)

        if (image.isDone){
            binding.imvBack.background = image.get()
        }

        binding.btnShowCreate.setOnClickListener {
            binding.cvCreate.visibility = View.VISIBLE
        }




//        binding.imvBack.background =

    }
}