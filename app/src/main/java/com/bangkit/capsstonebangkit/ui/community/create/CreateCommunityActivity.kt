package com.bangkit.capsstonebangkit.ui.community.create

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.CommunityCreateRequest
import com.bangkit.capsstonebangkit.data.api.model.CommunityJoinRequest
import com.bangkit.capsstonebangkit.databinding.ActivityCreateCommunityBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.community.CommunityActivity
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateCommunityActivity : BaseActivity<ActivityCreateCommunityBinding>() {

    override fun getViewBinding() = ActivityCreateCommunityBinding.inflate(layoutInflater)

    private val createCommunityViewModel by viewModel<CreateCommunityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        val clipData = ClipData.newPlainText("code", "123456")
//        clipboardManager.setPrimaryClip(clipData)

//        val image = Glide.with(this).load("oke").submit()
//        if (image.isDone){
//            binding.imvBack.background = image.get()
//        }

        createCommunityViewModel.createCommunityResponse.observe(this) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {
                            Toast.makeText(this, "Komunitas berhasil dibuat", Toast.LENGTH_SHORT)
                                .show()
                            //go to community page
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }

        createCommunityViewModel.joinCommunityResponse.observe(this) {
            when (it.status) {

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when (it.data?.code()) {
                        //sukses
                        200 -> {
                            Toast.makeText(this, "Berhasil join komunitas", Toast.LENGTH_SHORT)
                                .show()
                            //go to community page
                        }

                    }
                }

                Status.ERROR -> {}

            }
        }

        binding.btnShowCreate.setOnClickListener {
            binding.cvCreate.visibility = View.VISIBLE
        }

        binding.btnCreate.setOnClickListener {
            val communityName = binding.etCommunityName.editText?.text.toString()
            val deskripsi = binding.etDeskripsi.editText?.text.toString()
            val createRequest =
                CommunityCreateRequest(
                    name = communityName,
                    description = deskripsi
                )

            createCommunityViewModel.createCommunity(request = createRequest)

        }

        binding.btnJoin.setOnClickListener {



            val token = binding.etCode.editText?.text.toString()
            val createRequest =
                CommunityJoinRequest(
                    token = token
                )

            createCommunityViewModel.joinCommunity(request = createRequest)

        }


//        binding.imvBack.background =

    }
}