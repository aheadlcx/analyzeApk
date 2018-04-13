package com.xiaomi.xmpush.thrift;

public enum d {
    DeviceInfo(1),
    AppInstallList(2),
    AppActiveList(3),
    Bluetooth(4),
    Location(5),
    Account(6),
    WIFI(7),
    Cellular(8),
    TopApp(9),
    BroadcastAction(10),
    BroadcastActionAdded(11),
    BroadcastActionRemoved(12),
    BroadcastActionReplaced(13),
    BroadcastActionDataCleared(14),
    BroadcastActionRestarted(15),
    BroadcastActionChanged(16),
    AppPermission(17),
    WifiDevicesMac(18),
    ActivityActiveTimeStamp(19),
    DeviceBaseInfo(20),
    DeviceInfoV2(21);
    
    private final int v;

    private d(int i) {
        this.v = i;
    }

    public static d a(int i) {
        switch (i) {
            case 1:
                return DeviceInfo;
            case 2:
                return AppInstallList;
            case 3:
                return AppActiveList;
            case 4:
                return Bluetooth;
            case 5:
                return Location;
            case 6:
                return Account;
            case 7:
                return WIFI;
            case 8:
                return Cellular;
            case 9:
                return TopApp;
            case 10:
                return BroadcastAction;
            case 11:
                return BroadcastActionAdded;
            case 12:
                return BroadcastActionRemoved;
            case 13:
                return BroadcastActionReplaced;
            case 14:
                return BroadcastActionDataCleared;
            case 15:
                return BroadcastActionRestarted;
            case 16:
                return BroadcastActionChanged;
            case 17:
                return AppPermission;
            case 18:
                return WifiDevicesMac;
            case 19:
                return ActivityActiveTimeStamp;
            case 20:
                return DeviceBaseInfo;
            case 21:
                return DeviceInfoV2;
            default:
                return null;
        }
    }

    public int a() {
        return this.v;
    }
}
