package com.bangkit.capsstonebangkit.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.net.toFile
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.application.MyApp
import com.bangkit.capsstonebangkit.databinding.ActivityCameraBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.analysis.AnalysisResultActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : BaseActivity<ActivityCameraBinding>() {

    override fun getViewBinding() = ActivityCameraBinding.inflate(layoutInflater)

    private var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var detector: FaceDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val highAccuracyOpts = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        detector = FaceDetection.getClient(highAccuracyOpts)

        //star camera if permission accepted
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            //request permission
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.btnKlik.setOnClickListener {
            val outputFileOptions =
                ImageCapture.OutputFileOptions.Builder(File.createTempFile("IMG", ".jpg")).build()
            imageCapture?.takePicture(outputFileOptions, cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(error: ImageCaptureException) {
                        Toast.makeText(this@CameraActivity, "coba lagi", Toast.LENGTH_SHORT).show()
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        Log.d(TAG, "onImageSaved: ${outputFileResults.savedUri}")
                        //do request to api
                    }
                })

            val intent = Intent(this, AnalysisResultActivity::class.java)
            startActivity(intent)
        }


    }

    fun detectFace(imageProxy: ImageProxy, inputImage: InputImage) {
        detector.process(inputImage)
            .addOnSuccessListener { faces ->

                imageProxy.close()

//                binding.btnKlik.isEnabled = faces.size != 0

                if (faces.size == 0) {
                    //disabled

                    binding.btnKlik.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.camera_button_inactive,
                        this.theme
                    )
                    binding.btnKlik.isEnabled = false

                }else{
                    binding.btnKlik.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.dashboard_nav_bar_button_background,
                        this.theme
                    )
                    binding.btnKlik.isEnabled = true
                }


            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.camFace.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, FaceAnalyzer())
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture, imageAnalyzer
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    inner class FaceAnalyzer : ImageAnalysis.Analyzer {

        @SuppressLint("UnsafeOptInUsageError")
        override fun analyze(imageProxy: ImageProxy) {

            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                detectFace(imageProxy, image)
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}