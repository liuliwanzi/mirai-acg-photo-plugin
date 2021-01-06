package org.photo.acg.photo.dao

import com.alibaba.fastjson.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request
import org.photo.acg.photo.bean.AcgPhotoBean
import org.yaml.snakeyaml.Yaml
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

object AcgPhotoDao {

    fun getOkhttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
            .hostnameVerifier { hostname, session -> true }
            .sslSocketFactory(initSSLSocketFactory(), initTrustManager())
            .build()
        return okHttpClient
    }

    public fun getAcgPhoto(): AcgPhotoBean {
        val resourceAsStream = AcgPhotoDao::class.java.classLoader.getResourceAsStream("config.yml")
        val yaml = Yaml()
        val map = yaml.loadAs(resourceAsStream, HashMap::class.java)
        val apikey = map.getOrDefault("apikey", "").toString()
        val r18 = map.getOrDefault("r18", "0").toString()
        val size1200 = map.getOrDefault("size1200", "true").toString()
        val url = "https://api.lolicon.app/setu?apikey=%s&r18=%s&size200=%s"
        val okHttpClient = getOkhttpClient()
        val request: Request = Request.Builder().url(String.format(url, apikey, r18, size1200)).build()
        println(request.url)
        val call = okHttpClient.newCall(request)
        val response = call.execute()
        return JSONObject.parseObject(response.body!!.string(), AcgPhotoBean::class.java)
    }

    private fun initSSLSocketFactory(): SSLSocketFactory {
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance("SSL")
            val xTrustArray = arrayOf(initTrustManager())
            sslContext.init(
                null,
                xTrustArray, SecureRandom()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }


        return sslContext!!.socketFactory
    }

    private fun initTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }
        }
    }

}