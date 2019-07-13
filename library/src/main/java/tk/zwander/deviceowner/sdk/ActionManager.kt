package tk.zwander.deviceowner.sdk

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class ActionManager private constructor(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ActionManager? = null
        
        fun getInstance(context: Context): ActionManager {
            if (instance == null) instance = ActionManager(context.applicationContext)
            
            return instance!!
        }
    }
    
    val service: IActionService?
        get() = context.adminService

    /**
     * Check if the caller is currently allowed to
     * execute Device Owner actions.
     */
    val isAllowed: Boolean
        get() = service?.isAllowed ?: false

    /**
     * Request the ability to execute Device Owner
     * actions.
     *
     * @param callback optional callback to receive
     * the user's grant decision
     */
    fun requestPermission(callback: IAdminRequestCallback?) {
        service?.requestPermission(callback)
    }

    /**
     * Hide or unhide an application. Hiding is similar to
     * disabling. The hidden app will not be able to execute any
     * functions or be opened/invoked while hidden.
     *
     * @param packageName the app to hide or unhide
     * @param hidden whether to hide or unhide the specified app
     *
     * @return whether the application's hide state was successfully
     * changed
     */
    fun setApplicationHidden(packageName: String, hidden: Boolean): Boolean {
        return service?.setApplicationHidden(packageName, hidden) ?: false
    }

    /**
     * Query whether an application is currently hidden.
     *
     * @param packageName the potentially hidden app
     *
     * @return whether the specified app is hidden
     */
    fun isApplicationHidden(packageName: String): Boolean {
        return service?.isApplicationHidden(packageName) ?: false
    }

    /**
     * Clear the specified app's user data.
     *
     * @param packageName the package name of the app whose data to clear
     * @param listener an optional listener to receive the result of
     * the data clear
     */
    @RequiresApi(Build.VERSION_CODES.P)
    fun clearApplicationUserData(packageName: String, listener: IOnClearApplicationDataListener?) {
        service?.clearApplicationUserData(packageName, listener)
    }
}