package tk.zwander.deviceowner.sdk;

import tk.zwander.deviceowner.sdk.IAdminRequestCallback;
import tk.zwander.deviceowner.sdk.IOnClearApplicationDataListener;

interface IActionService {
    boolean isAllowed() = 1;
    void requestPermission(in IAdminRequestCallback callback) = 2;

    boolean setApplicationHidden(String packageName, boolean hidden) = 3;
    boolean isApplicationHidden(String packageName) = 4;

    //Can only be called on API >= 24
    String[] setPackagesSuspended(in String[] packageNames, boolean suspended) = 6;
    boolean isPackageSuspended(String packageName) = 7;

    //Can only be called on API >= 28
    void clearApplicationUserData(String packageName, in IOnClearApplicationDataListener listener) = 5;
}
