package com.bangkit.capsstonebangkit.data.api

import com.bangkit.capsstonebangkit.data.api.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

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

    suspend fun getProfile() = apiService.getProfile()

    //community
    suspend fun getCommunities() = apiService.getCommunities()

    suspend fun createCommunity(request: CommunityCreateRequest) =
        apiService.createCommunity(request)

    suspend fun joinCommunity(request: CommunityJoinRequest) =
        apiService.joinCommunity(request)

}