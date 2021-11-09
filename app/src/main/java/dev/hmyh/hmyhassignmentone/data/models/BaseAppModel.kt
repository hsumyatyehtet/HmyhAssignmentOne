package dev.hmyh.hmyhassignmentone.data.models

import android.content.Context
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dev.hmyh.hmyhassignmentone.BuildConfig
import dev.hmyh.hmyhassignmentone.network.HmyhAssignmentOneApi
import dev.hmyh.hmyhassignmentone.persistence.HmyhAssignmentOneDatabase
import dev.hmyh.hmyhassignmentone.utils.ApiConstants
import dev.hmyh.hmyhassignmentone.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseAppModel: BaseModel() {

    protected lateinit var mHmyhAssignmentOneApi: HmyhAssignmentOneApi
    protected lateinit var mDatabase: HmyhAssignmentOneDatabase

    override fun init(context: Context) {
        initNetwork(context)
        initDatabase(context)
    }

    private fun initDatabase(context: Context) {
        mDatabase = HmyhAssignmentOneDatabase.getDatabase(context)
    }


    private fun initNetwork(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(ChuckInterceptor(context))
            }
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(ApiConstants.HEADER_ACCEPT, ApiConstants.HEADER_VALUE)
                        .addHeader(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.HEADER_VALUE)
                        .build()
                )
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mHmyhAssignmentOneApi = retrofit.create(HmyhAssignmentOneApi::class.java)
    }

}