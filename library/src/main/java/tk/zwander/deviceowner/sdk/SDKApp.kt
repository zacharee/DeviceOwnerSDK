package tk.zwander.deviceowner.sdk

import android.app.Application

open class SDKApp : Application() {
    override fun onCreate() {
        super.onCreate()

        allowHiddenAPIs()
    }
}