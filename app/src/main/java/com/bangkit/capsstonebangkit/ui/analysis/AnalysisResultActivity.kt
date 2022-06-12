package com.bangkit.capsstonebangkit.ui.analysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.PredictResponse
import com.bangkit.capsstonebangkit.databinding.ActivityAnalysisResultBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnalysisResultActivity : BaseActivity<ActivityAnalysisResultBinding>() {

    override fun getViewBinding() = ActivityAnalysisResultBinding.inflate(layoutInflater)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val analysisResult = intent.getParcelableExtra<PredictResponse>("analysis_result")

        binding.sbEyesSlider.setOnTouchListener { _, _ -> true }
        binding.sbHangingEyes.setOnTouchListener { _, _ -> true }


        Glide.with(this).load(analysisResult?.prediction?.image).into(binding.imgFace)


        binding.sbHangingEyes.progress = 30





    }
}