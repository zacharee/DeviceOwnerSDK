package tk.zwander.deviceowner.sdk;

interface IAdminRequestCallback {
    void onResult(String packageName, boolean granted);
}
