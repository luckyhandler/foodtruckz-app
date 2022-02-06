package de.handler.foodtruckz.feature.overview.data

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.ProtocolException


private val auth: String = Base64.encodeToString(
    ("token" + ":" + Keys.token).toByteArray(),
    Base64.NO_WRAP,
)

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return proceed(chain, try {
            originalRequest.newBuilder().apply {
                header(
                    "Authorization",
                    "Basic $auth",
                )
            }.build()
        } catch (e: Exception) {
            originalRequest
        })
    }
}

fun proceed(chain: Interceptor.Chain, request: Request): Response {
    return try {
        chain.proceed(request)
    } catch (e: IOException) {
        chain.proceed(chain.request())
    } catch (e: ProtocolException) {
        chain.proceed(chain.request())
    }
}