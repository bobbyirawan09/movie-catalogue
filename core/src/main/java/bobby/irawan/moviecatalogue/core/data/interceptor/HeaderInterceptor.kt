package bobby.irawan.moviecatalogue.core.data.interceptor

import bobby.irawan.moviecatalogue.core.BuildConfig.API_KEY
import bobby.irawan.moviecatalogue.core.utils.Constants.QUERY_PARAM_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Bobby Irawan on 25/10/20.
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url =
            chain.request().url.newBuilder().addQueryParameter(QUERY_PARAM_API_KEY, API_KEY).build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}