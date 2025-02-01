package com.sougata.auth.domain

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String):EmptyResult<DataError.Network>
    suspend fun sendOtp(email: String): EmptyResult<DataError.Network>
    suspend fun verifyOtp(email: String, otp: String): EmptyResult<DataError.Network>
    suspend fun resetPassword(email: String, password: String): EmptyResult<DataError.Network>
}