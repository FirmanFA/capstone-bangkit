package com.bangkit.capsstonebangkit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.data.Status
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.data.api.model.RegisterRequest
import com.bangkit.capsstonebangkit.databinding.ActivityLoginBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.sessionResponse.observe(this){
            when(it.status){
                Status.LOADING->{}
                Status.SUCCESS->{
                    when(it.data?.code()){
                        200->{
                            binding.tvSession.text = "sudah login"
                        }
                    }
                }
                Status.ERROR->{}
            }
        }
        loginViewModel.checkSession()

        loginViewModel.loginResponse.observe(this){
            when(it.status){

                Status.LOADING -> {}

                Status.SUCCESS -> {
                    when(it.data?.code()){
                        //sukses
                        200 ->{
                            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show()
                            loginViewModel.checkSession()
                        }
                        //password salah
                        401 ->{
                            Toast.makeText(this, "harap perhatikan password anda", Toast.LENGTH_SHORT).show()
                        }
                        //user tidak ditemukan
                        404 ->{
                            Toast.makeText(this, "user tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                Status.ERROR ->{}

            }
        }

        binding.apply {
            btnLogin.setOnClickListener{
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val loginRequest = LoginRequest(email, password)
                loginViewModel.postLogin(loginRequest)
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }



}