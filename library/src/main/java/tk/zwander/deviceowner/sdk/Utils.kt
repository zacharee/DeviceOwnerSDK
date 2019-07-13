package tk.zwander.deviceowner.sdk

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.RemoteException
import java.lang.reflect.Method

val serviceComponent = ComponentName("tk.zwander.deviceowner", "tk.zwander.deviceowner.api.ActionRequestService")

val Context.adminBinder: IBinder?
    get() {
        return peekService(Intent().setComponent(serviceComponent))
    }

val IBinder.adminService: IActionService
    get() {
        return IActionService.Stub.asInterface(this)
    }

val Context.adminService: IActionService?
    get() {
        return adminBinder?.adminService
    }

val Context.actionManager: ActionManager
    get() = ActionManager.getInstance(this)

fun Context.peekService(service: Intent): IBinder? {
    val am = ActivityManager.getService()
    var binder: IBinder? = null
    try {
        service.prepareToLeaveProcess(this)
        binder = am.peekService(
            service, service.resolveTypeIfNeeded(
                contentResolver
            ), opPackageName
        )
    } catch (e: RemoteException) {
    }

    return binder
}

fun allowHiddenAPIs() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val forName = Class::class.java.getDeclaredMethod("forName", String::class.java)
        val getDeclaredMethod = Class::class.java.getDeclaredMethod("getDeclaredMethod", String::class.java, arrayOf<Class<*>>()::class.java)

        val vmRuntimeClass = forName.invoke(null, "dalvik.system.VMRuntime") as Class<*>
        val getRuntime = getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null) as Method
        val setHiddenApiExemptions = getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", arrayOf(arrayOf<String>()::class.java)) as Method

        val vmRuntime = getRuntime.invoke(null)

        setHiddenApiExemptions.invoke(vmRuntime, arrayOf("L"))
    }
}