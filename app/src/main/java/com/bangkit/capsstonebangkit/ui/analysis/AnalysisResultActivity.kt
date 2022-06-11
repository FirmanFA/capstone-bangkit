package com.bangkit.capsstonebangkit.ui.analysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.bangkit.capsstonebangkit.databinding.ActivityAnalysisResultBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity

class AnalysisResultActivity : BaseActivity<ActivityAnalysisResultBinding>() {

    override fun getViewBinding() = ActivityAnalysisResultBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.sbEyesSlider.setOnTouchListener { _, _ -> true }
        binding.sbHangingEyes.setOnTouchListener { _, _ -> true }

        binding.sbHangingEyes.progress = 30


    }
}