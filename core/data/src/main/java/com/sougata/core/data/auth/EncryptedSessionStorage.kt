package com.sougata.core.data.auth

import android.content.SharedPreferences
import com.sougata.core.domain.AuthInfo
import com.sougata.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences
): SessionStorage {
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO){
            val jsonString = sharedPreferences.getString(KEY_AUTH_INFO, null)
            jsonString?.let {
                Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
            }
        }
    }

    override suspend fun set(authInfo: AuthInfo?) {

        if (authInfo == null){
            sharedPreferences.edit().remove(KEY_AUTH_INFO).apply()
        }else{
            val jsonString = Json.encodeToString(authInfo.toAuthInfoSerializable())
            sharedPreferences.edit().putString(KEY_AUTH_INFO, jsonString).apply()
        }
    }

    companion object{
        const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }

}