package dev.hmyh.hmyhassignmentone

import android.app.Application
import android.content.Context
import dev.hmyh.hmyhassignmentone.data.models.impl.HmyhAssignmentOneModelImpl

class HmyhAssignmentOneApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        application = applicationContext

        HmyhAssignmentOneModelImpl.init(applicationContext)

    }

    companion object{
        const val TAG="HmyhAssignmentOneApp"
        lateinit var application: Context
    }

}