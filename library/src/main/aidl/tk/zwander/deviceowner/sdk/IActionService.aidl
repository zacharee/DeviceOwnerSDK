package tk.zwander.deviceowner.sdk;

import tk.zwander.deviceowner.sdk.IAdminRequestCallback;

interface IActionService {
    boolean setApplicationHidden(String packageName, boolean hidden);
    boolean isApplicationHidden(String packageName);

    boolean isAllowed();
    void requestPermission(in IAdminRequestCallback callback);
}
