package com.alibaba.mtl.appmonitor;

import android.app.Application;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.IMonitor.Stub;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.e.i;
import java.util.Map;

public class Monitor extends Stub {
    private Application b;

    Monitor(Application application) {
        this.b = application;
    }

    public void init() throws RemoteException {
        AppMonitorDelegate.init(this.b);
    }

    public void destroy() throws RemoteException {
        AppMonitorDelegate.destroy();
    }

    public void triggerUpload() throws RemoteException {
        AppMonitorDelegate.triggerUpload();
    }

    public void setSampling(int i) throws RemoteException {
        AppMonitorDelegate.setSampling(i);
    }

    public void enableLog(boolean z) throws RemoteException {
        AppMonitorDelegate.enableLog(z);
    }

    public void setStatisticsInterval2(int i, int i2) throws RemoteException {
        AppMonitorDelegate.setStatisticsInterval(a(i), i2);
    }

    public void setRequestAuthInfo(boolean z, String str, String str2, String str3) throws RemoteException {
        AppMonitorDelegate.setRequestAuthInfo(z, str, str2, str3);
    }

    public void setChannel(String str) throws RemoteException {
        AppMonitorDelegate.setChannel(str);
    }

    public void turnOnRealTimeDebug(Map map) throws RemoteException {
        AppMonitorDelegate.turnOnRealTimeDebug(map);
    }

    public void turnOffRealTimeDebug() throws RemoteException {
        AppMonitorDelegate.turnOffRealTimeDebug();
    }

    public void counter_setStatisticsInterval(int i) throws RemoteException {
        AppMonitorDelegate$Counter.setStatisticsInterval(i);
    }

    public void counter_setSampling(int i) throws RemoteException {
        AppMonitorDelegate$Counter.setSampling(i);
    }

    public boolean counter_checkSampled(String str, String str2) throws RemoteException {
        return AppMonitorDelegate$Counter.checkSampled(str, str2);
    }

    public void counter_commit1(String str, String str2, double d) throws RemoteException {
        AppMonitorDelegate$Counter.commit(str, str2, d);
    }

    public void counter_commit2(String str, String str2, String str3, double d) throws RemoteException {
        AppMonitorDelegate$Counter.commit(str, str2, str3, d);
    }

    public void alarm_setStatisticsInterval(int i) throws RemoteException {
        AppMonitorDelegate$Alarm.setStatisticsInterval(i);
    }

    public void alarm_setSampling(int i) throws RemoteException {
        AppMonitorDelegate$Alarm.setSampling(i);
    }

    public boolean alarm_checkSampled(String str, String str2) throws RemoteException {
        return AppMonitorDelegate$Alarm.checkSampled(str, str2);
    }

    public void alarm_commitSuccess1(String str, String str2) throws RemoteException {
        AppMonitorDelegate$Alarm.commitSuccess(str, str2);
    }

    public void alarm_commitSuccess2(String str, String str2, String str3) throws RemoteException {
        AppMonitorDelegate$Alarm.commitSuccess(str, str2, str3);
    }

    public void alarm_commitFail1(String str, String str2, String str3, String str4) throws RemoteException {
        AppMonitorDelegate$Alarm.commitFail(str, str2, str3, str4);
    }

    public void alarm_commitFail2(String str, String str2, String str3, String str4, String str5) throws RemoteException {
        AppMonitorDelegate$Alarm.commitFail(str, str2, str3, str4, str5);
    }

    public void offlinecounter_setStatisticsInterval(int i) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.setStatisticsInterval(i);
    }

    public void offlinecounter_setSampling(int i) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.setSampling(i);
    }

    public boolean offlinecounter_checkSampled(String str, String str2) throws RemoteException {
        return AppMonitorDelegate$OffLineCounter.checkSampled(str, str2);
    }

    public void offlinecounter_commit(String str, String str2, double d) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.commit(str, str2, d);
    }

    public void setStatisticsInterval1(int i) throws RemoteException {
        AppMonitorDelegate.setStatisticsInterval(i);
    }

    public void register1(String str, String str2, MeasureSet measureSet) throws RemoteException {
        AppMonitorDelegate.register(str, str2, measureSet);
    }

    public void register2(String str, String str2, MeasureSet measureSet, boolean z) throws RemoteException {
        AppMonitorDelegate.register(str, str2, measureSet, z);
    }

    public void register3(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) throws RemoteException {
        AppMonitorDelegate.register(str, str2, measureSet, dimensionSet);
    }

    public void register4(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) throws RemoteException {
        AppMonitorDelegate.register(str, str2, measureSet, dimensionSet, z);
    }

    public void stat_begin(String str, String str2, String str3) throws RemoteException {
        AppMonitorDelegate$Stat.begin(str, str2, str3);
    }

    public void stat_end(String str, String str2, String str3) throws RemoteException {
        AppMonitorDelegate$Stat.end(str, str2, str3);
    }

    public void stat_setStatisticsInterval(int i) throws RemoteException {
        AppMonitorDelegate$Stat.setStatisticsInterval(i);
    }

    public void stat_setSampling(int i) throws RemoteException {
        AppMonitorDelegate$Stat.setSampling(i);
    }

    public boolean stat_checkSampled(String str, String str2) throws RemoteException {
        return AppMonitorDelegate$Stat.checkSampled(str, str2);
    }

    public void stat_commit1(String str, String str2, double d) throws RemoteException {
        AppMonitorDelegate$Stat.commit(str, str2, d);
    }

    public void stat_commit2(String str, String str2, DimensionValueSet dimensionValueSet, double d) throws RemoteException {
        AppMonitorDelegate$Stat.commit(str, str2, dimensionValueSet, d);
    }

    public void stat_commit3(String str, String str2, DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet) throws RemoteException {
        i.a("Monitor", "[stat_commit3]");
        AppMonitorDelegate$Stat.commit(str, str2, dimensionValueSet, measureValueSet);
    }

    private f a(int i) {
        return f.a(i);
    }

    public void transaction_begin(Transaction transaction, String str) throws RemoteException {
        TransactionDelegate.begin(transaction, str);
    }

    public void transaction_end(Transaction transaction, String str) throws RemoteException {
        TransactionDelegate.end(transaction, str);
    }

    public void updateMeasure(String str, String str2, String str3, double d, double d2, double d3) throws RemoteException {
        AppMonitorDelegate.updateMeasure(str, str2, str3, d, d2, d3);
    }
}
