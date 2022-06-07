package com.bangkit.capsstonebangkit.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.databinding.ActivityDashboardBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.analysis.AnalysisResultActivity
import com.bangkit.capsstonebangkit.ui.camera.CameraActivity
import com.bangkit.capsstonebangkit.ui.community.create.CreateCommunityActivity
import com.bangkit.capsstonebangkit.utils.HorizontalMarginItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        dashboardViewModel.profileResponse.observe(this){


            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Glide.with(this)
                                .load(it.data.body()?.image)
                                .circleCrop()
                                .into(binding.ivProfile)

                            binding.tvName.text = it.data.body()?.username
                        }

                    }
                }

                Status.ERROR ->{}

            }

        }

        dashboardViewModel.getProfile()


//        binding.button.setOnClickListener {
//            PreferenceManager
//                .getDefaultSharedPreferences(this)
//                .edit()
//                .clear()
//                .apply()
//
//            finish()
//        }

        val listImage = listOf(
            R.drawable.dashboard_banner_image_1,
            R.drawable.dashboard_banner_image_1,
            R.drawable.dashboard_banner_image_1,
        )


        val adapter = DashboardBannerAdapter(listImage)
        binding.vpDashboardBanner.adapter = adapter
        binding.vpDashboardBanner.offscreenPageLimit = 1
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )

        binding.vpDashboardBanner.addItemDecoration(itemDecoration)
        TabLayoutMediator(binding.tabDashboardBanner, binding.vpDashboardBanner) { _, _ ->}.attach()

        Glide
            .with(this)
            .load(R.drawable.default_avatar)
            .circleCrop()
            .into(binding.ivProfile)

        binding.btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnRehatHelp.setOnClickListener {
            val intent = Intent(this, AnalysisResultActivity::class.java)
            startActivity(intent)
        }
        binding.btnRehatEdukasi.setOnClickListener {
            val intent = Intent(this, CreateCommunityActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        finishAffinity()
    }

}