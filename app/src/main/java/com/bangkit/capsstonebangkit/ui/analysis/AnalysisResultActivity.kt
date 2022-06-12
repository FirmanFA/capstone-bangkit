package com.bangkit.capsstonebangkit.ui.analysis

import android.os.Bundle
import com.bangkit.capsstonebangkit.data.api.model.PredictResponse
import com.bangkit.capsstonebangkit.databinding.ActivityAnalysisResultBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class AnalysisResultActivity : BaseActivity<ActivityAnalysisResultBinding>() {

    override fun getViewBinding() = ActivityAnalysisResultBinding.inflate(layoutInflater)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imvBack.setOnClickListener {
            onBackPressed()
        }

        val analysisResult = intent.getParcelableExtra<PredictResponse>("analysis_result")


        val current = Calendar.getInstance().time

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val formatted = sdf.format(current)

        Glide.with(this).load(analysisResult?.prediction?.image).into(binding.imgFace)
        binding.apply {
            tvTime.text = formatted
            tvEyeLidResult.text = analysisResult?.prediction?.eyelidCondition
            tvEyeBagResult.text = analysisResult?.prediction?.eyebagCondition
            tvHeader.text = analysisResult?.prediction?.finalCondition?.header
            tvDetail.text = analysisResult?.prediction?.finalCondition?.detail
        }






    }
}