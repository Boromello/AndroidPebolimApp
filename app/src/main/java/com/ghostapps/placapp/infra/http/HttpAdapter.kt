package com.ghostapps.placapp.infra.http

import okhttp3.OkHttpClient
import okhttp3.Request

class HttpAdapter {

    var client = OkHttpClient.Builder().build()

    fun get(url: String): String? {
        try {
            val request = Request.Builder().url(url).get().build()
            val response = client.newCall(request).execute()
            return response.body?.string()
        } catch (error: Error) { }
        return null
    }

}