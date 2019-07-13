package tk.zwander.deviceowner.sdk;

interface IOnClearApplicationDataListener {
    void onApplicationUserDataCleared(String packageName, boolean succeeded);
}
