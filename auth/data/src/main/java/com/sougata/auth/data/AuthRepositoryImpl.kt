package com.sougata.auth.data

import com.sougata.auth.domain.AuthRepository
import com.sougata.core.data.networking.ResetPassRequest
import com.sougata.core.data.networking.VerifyOtpRequest
import com.sougata.core.data.networking.post
import com.sougata.core.domain.AuthInfo
import com.sougata.core.domain.SessionStorage
import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.EmptyResult
import com.sougata.core.domain.util.Result
import com.sougata.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {

        val result = httpClient.post<LoginRequest,LoginResponse>(
            route = "/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            ),
        )
        if (result is Result.Success){
            sessionStorage.set(AuthInfo(refreshToken = result.data.data.refreshToken, accessToken = result.data.data.accessToken))
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {

        return httpClient.post<RegisterRequest, Unit>(
            route = "/auth/register",
            body = RegisterRequest(
                name = "pser 456",
                email = email,
                password = password
            )
        )
    }

    override suspend fun sendOtp(email: String): EmptyResult<DataError.Network> {
        return httpClient.post<Unit,Unit>(
            route = "/auth/send-password-reset-otp",
            queryParameters = mapOf(
                "email" to email
            ),
            body = Unit
        )
    }

    override suspend fun verifyOtp(email: String, otp: String): EmptyResult<DataError.Network> {
        return httpClient.post<VerifyOtpRequest,Unit>(
            route = "/auth/verify-otp",
            body = VerifyOtpRequest(
                email = email,
                otp = otp
            )
        )
    }

    override suspend fun resetPassword(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<ResetPassRequest,Unit>(
            route = "/auth/change-password",
            body = ResetPassRequest(
                email = email,
                password = password
            )
        )
    }

}