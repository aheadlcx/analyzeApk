package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.iflytek.speech.SynthesizerListener;

public interface ISpeechSynthesizer extends IInterface {

    public static abstract class Stub extends Binder implements ISpeechSynthesizer {
        private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeechSynthesizer";
        static final int TRANSACTION_getLocalSpeakerList = 7;
        static final int TRANSACTION_isSpeaking = 6;
        static final int TRANSACTION_pauseSpeaking = 3;
        static final int TRANSACTION_resumeSpeaking = 4;
        static final int TRANSACTION_startSpeaking = 2;
        static final int TRANSACTION_stopSpeaking = 5;
        static final int TRANSACTION_synthesizeToUrl = 1;

        private static class Proxy implements ISpeechSynthesizer {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public String getLocalSpeakerList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isSpeaking() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int pauseSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int resumeSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int startSpeaking(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int stopSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int synthesizeToUrl(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpeechSynthesizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISpeechSynthesizer)) ? new Proxy(iBinder) : (ISpeechSynthesizer) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Intent intent = null;
            int synthesizeToUrl;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    synthesizeToUrl = synthesizeToUrl(intent, com.iflytek.speech.SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    synthesizeToUrl = startSpeaking(intent, com.iflytek.speech.SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    synthesizeToUrl = pauseSpeaking(com.iflytek.speech.SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    synthesizeToUrl = resumeSpeaking(com.iflytek.speech.SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    synthesizeToUrl = stopSpeaking(com.iflytek.speech.SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSpeaking = isSpeaking();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSpeaking ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String localSpeakerList = getLocalSpeakerList();
                    parcel2.writeNoException();
                    parcel2.writeString(localSpeakerList);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String getLocalSpeakerList() throws RemoteException;

    boolean isSpeaking() throws RemoteException;

    int pauseSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int resumeSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int startSpeaking(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException;

    int stopSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int synthesizeToUrl(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException;
}
