package com.bangkit.capsstonebangkit.data.api

import com.bangkit.capsstonebangkit.data.api.model.ForgetPasswordRequest
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.data.api.model.RegisterRequest
import com.bangkit.capsstonebangkit.data.api.model.UpdatePasswordRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ApiHelper(private val apiService: ApiService) {

    suspend fun postRegister(
        username: RequestBody,
        email: RequestBody,
        password: RequestBody,
        image: MultipartBody.Part
    ) = apiService.postRegister(username, email, password, image)

    suspend fun postLogin(request: LoginRequest) = apiService.postLogin(request)

    suspend fun postForgetPassword(request: ForgetPasswordRequest) =
        apiService.postForgetPassword(request)

    suspend fun postUpdatePassword(request: UpdatePasswordRequest) =
        apiService.postUpdatePassword(request)

    suspend fun checkSession() = apiService.checkSession()

}