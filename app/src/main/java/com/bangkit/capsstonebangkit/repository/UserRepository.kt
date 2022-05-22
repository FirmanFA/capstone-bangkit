package com.bangkit.capsstonebangkit.repository

import com.bangkit.capsstonebangkit.data.api.ApiHelper
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.data.api.model.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun postRegister(username: RequestBody,
                             email: RequestBody,
                             password: RequestBody,
                             image: MultipartBody.Part) = apiHelper.postRegister(username,
                                                                                email,
                                                                                password,
                                                                                image)

    suspend fun postLogin(request: LoginRequest) = apiHelper.postLogin(request)
    suspend fun checkSession() = apiHelper.checkSession()

}