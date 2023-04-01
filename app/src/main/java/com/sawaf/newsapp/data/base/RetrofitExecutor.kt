package com.sawaf.newsapp.data.base

import android.content.Context
import com.sawaf.newsapp.R
import com.sawaf.newsapp.core.utils.NetworkHelper
import com.sawaf.newsapp.domain.common.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Network operation executor for retrofit library
 */
class RetrofitExecutor @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<BaseResponse<T>>
    ): Result<T> {
        return if (NetworkHelper().isConnected(context)) {
            try {
                val data = safeApiResult(call)
                Timber.i(data.status)
                return when (data.status) {
                    "ok" -> {
                        Result.Success(data.data)
                    }
                    "error" -> {
                        Result.Error(Exception("Server error"))
                    }
                    else -> {
                        Result.Error(Exception("Something Wrong Happen"))
                    }
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        } else {
            Result.Error(Exception(context.getString(R.string.no_internet_connection)))
        }
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): T {
        val response = call()
        return when (response.isSuccessful) {
            false -> throw Exception("Something went wrong")
            else -> response.body() ?: throw Exception("Null response")
        }
    }
}