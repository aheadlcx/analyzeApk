package com.liulishuo.filedownloader.i;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.liulishuo.filedownloader.message.MessageSnapshot;

public interface IFileDownloadIPCCallback extends IInterface {

    public static abstract class Stub extends Binder implements IFileDownloadIPCCallback {

        private static class a implements IFileDownloadIPCCallback {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getInterfaceDescriptor() {
                return "com.liulishuo.filedownloader.i.IFileDownloadIPCCallback";
            }

            public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCCallback");
                    if (messageSnapshot != null) {
                        obtain.writeInt(1);
                        messageSnapshot.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.liulishuo.filedownloader.i.IFileDownloadIPCCallback");
        }

        public static IFileDownloadIPCCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFileDownloadIPCCallback)) {
                return new a(iBinder);
            }
            return (IFileDownloadIPCCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    MessageSnapshot messageSnapshot;
                    parcel.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCCallback");
                    if (parcel.readInt() != 0) {
                        messageSnapshot = (MessageSnapshot) MessageSnapshot.CREATOR.createFromParcel(parcel);
                    } else {
                        messageSnapshot = null;
                    }
                    callback(messageSnapshot);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.liulishuo.filedownloader.i.IFileDownloadIPCCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void callback(MessageSnapshot messageSnapshot) throws RemoteException;
}
