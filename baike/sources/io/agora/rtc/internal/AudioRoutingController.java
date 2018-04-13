package io.agora.rtc.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.agora.rtc.Constants;
import java.lang.ref.WeakReference;
import java.util.List;

public class AudioRoutingController {
    private static final int BLUETOOTH_SCO_TIMEOUT_MS = 1500;
    private static final int BT_SCO_STATE_CONNECTED = 1;
    private static final int BT_SCO_STATE_CONNECTING = 0;
    private static final int BT_SCO_STATE_DISCONNECTED = 3;
    private static final int BT_SCO_STATE_DISCONNECTING = 2;
    public static final int CMD_FORCE_TO_SPEAKER = 11;
    public static final int CMD_MUTE_VIDEO_ALL = 14;
    public static final int CMD_MUTE_VIDEO_LOCAL = 12;
    public static final int CMD_MUTE_VIDEO_REMOTES = 13;
    public static final int CMD_SET_DEFAULT_ROUTING = 10;
    public static final int CMD_START_BT_SCO = 15;
    private static final int ERROR = 4;
    private static final int EVT_BT_HEADSET = 2;
    private static final int EVT_BT_SCO = 3;
    public static final int EVT_CHANNEL_PROFILE = 20;
    public static final int EVT_ENGINE_ROLE_CHANGED = 21;
    private static final int EVT_HEADSET = 1;
    public static final int EVT_PHONE_STATE_CHANGED = 22;
    private static final int MAX_SCO_CONNECT_ATTEMPS = 4;
    public static final int OFF = 0;
    public static final int ON = 1;
    private static final int START = 1;
    private static final int STOP = 2;
    public static final String TAG = "AudioRoute";
    private static final int UNINIT = 0;
    public static final int UNSET = -1;
    private final Runnable bluetoothTimeoutRunnable = new Runnable() {
        public void run() {
            AudioRoutingController.this.bluetoothTimeout();
        }
    };
    private BluetoothAdapter mBTAdapter;
    private BluetoothHeadset mBTHeadset;
    private ServiceListener mBTHeadsetListener;
    private BTHeadsetBroadcastReceiver mBTHeadsetReceiver;
    private int mBtScoState = 3;
    private int mChannelProfile = 1;
    private WeakReference<Context> mContext;
    private int mCurrentRouting = -1;
    private int mDefaultRouting = -1;
    private int mEngineRole = -1;
    private EventHandler mEventHandler;
    private int mForceSpeakerphone = -1;
    private HeadsetBroadcastReceiver mHeadsetReceiver;
    private int mHeadsetType = -1;
    private boolean mIsBTHeadsetPlugged = false;
    private boolean mIsBTScoStarted = false;
    private boolean mIsWiredHeadsetPlugged = false;
    private WeakReference<AudioRoutingListener> mListener;
    private boolean mMuteLocal = false;
    private boolean mMuteRemotes = false;
    private boolean mPhoneInCall = false;
    private int mScoConnectionAttemps;
    private ControllerState mState;
    private boolean mUserOverrideDefault = false;
    private boolean mVideoDisabled = true;

    public interface AudioRoutingListener {
        void onAudioRoutingChanged(int i);

        void onAudioRoutingError(int i);
    }

    private class BTHeadsetBroadcastReceiver extends BroadcastReceiver {
        private BTHeadsetBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int intExtra;
            BluetoothDevice bluetoothDevice;
            if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                switch (intExtra) {
                    case 0:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth device " + bluetoothDevice + " disconnected");
                        AudioRoutingController.this.sendEvent(2, 0);
                        return;
                    case 1:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth device " + bluetoothDevice + " connecting");
                        return;
                    case 2:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth device " + bluetoothDevice + " connected");
                        AudioRoutingController.this.mScoConnectionAttemps = 0;
                        AudioRoutingController.this.sendEvent(2, 1);
                        return;
                    case 3:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth device " + bluetoothDevice + " disconnecting");
                        return;
                    default:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth device " + bluetoothDevice + " unknown event, state=" + intExtra);
                        return;
                }
            } else if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                switch (intExtra) {
                    case 10:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth a2dp device " + bluetoothDevice + " disconnected");
                        return;
                    case 11:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth a2dp device " + bluetoothDevice + " connecting");
                        return;
                    case 12:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth a2dp device " + bluetoothDevice + " connected");
                        return;
                    default:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth audio device " + bluetoothDevice + " event, state=" + intExtra);
                        return;
                }
            } else if (action.equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
                int intExtra2 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
                switch (intExtra2) {
                    case -1:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth SCO device error");
                        return;
                    case 0:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth SCO device disconnected");
                        AudioRoutingController.this.sendEvent(3, 0);
                        return;
                    case 1:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth SCO device connected");
                        AudioRoutingController.this.cancelTimer();
                        AudioRoutingController.this.sendEvent(3, 1);
                        return;
                    case 2:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth SCO device connecting");
                        return;
                    default:
                        Logging.i(AudioRoutingController.TAG, "Bluetooth SCO device unknown event, state=" + intExtra2);
                        return;
                }
            } else if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1)) {
                    case 10:
                        AudioRoutingController.this.sendEvent(2, 0);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private interface ControllerState {
        int getState();

        void onEvent(int i, int i2);

        void setState(int i);
    }

    private abstract class ControllerBaseState implements ControllerState {
        private ControllerBaseState() {
        }

        public void setState(int i) {
            if (i == getState()) {
                Logging.i(AudioRoutingController.TAG, "setState: state not changed!");
            } else {
                AudioRoutingController.this.mState = AudioRoutingController.this.changeState(i);
            }
        }

        public int getState() {
            return 0;
        }

        public void onEvent(int i, int i2) {
            boolean z = true;
            AudioRoutingController audioRoutingController;
            switch (i) {
                case 1:
                    AudioRoutingController.this.mHeadsetType = i2;
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 < 0) {
                        z = false;
                    }
                    audioRoutingController.mIsWiredHeadsetPlugged = z;
                    return;
                case 2:
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 != 1) {
                        z = false;
                    }
                    audioRoutingController.mIsBTHeadsetPlugged = z;
                    return;
                case 10:
                    AudioRoutingController.this.mDefaultRouting = i2;
                    AudioRoutingController.this.mUserOverrideDefault = true;
                    Logging.i(AudioRoutingController.TAG, "User set default routing to:" + AudioRoutingController.this.getAudioRouteDesc(AudioRoutingController.this.mDefaultRouting));
                    return;
                case 12:
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 <= 0) {
                        z = false;
                    }
                    audioRoutingController.mMuteLocal = z;
                    AudioRoutingController.this.updateDefaultRouting();
                    return;
                case 13:
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 <= 0) {
                        z = false;
                    }
                    audioRoutingController.mMuteRemotes = z;
                    AudioRoutingController.this.updateDefaultRouting();
                    return;
                case 14:
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 <= 0) {
                        z = false;
                    }
                    audioRoutingController.mVideoDisabled = z;
                    AudioRoutingController.this.updateDefaultRouting();
                    return;
                case 20:
                    AudioRoutingController.this.mChannelProfile = i2;
                    return;
                case 21:
                    AudioRoutingController.this.mEngineRole = i2;
                    return;
                case 22:
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 <= 0) {
                        z = false;
                    }
                    audioRoutingController.mPhoneInCall = z;
                    return;
                default:
                    return;
            }
        }
    }

    private class ControllerErrorState extends ControllerBaseState {
        private ControllerErrorState() {
            super();
        }

        public int getState() {
            return 4;
        }
    }

    private class ControllerStartState extends ControllerBaseState {
        public ControllerStartState() {
            super();
            if (!AudioRoutingController.this.mUserOverrideDefault && AudioRoutingController.this.mDefaultRouting == -1) {
                if (AudioRoutingController.this.mChannelProfile == 0 && AudioRoutingController.this.isAudioOnly()) {
                    AudioRoutingController.this.mDefaultRouting = 1;
                } else {
                    AudioRoutingController.this.mDefaultRouting = 3;
                }
            }
            AudioRoutingController.this.resetAudioRouting();
            Logging.i(AudioRoutingController.TAG, "Monitor start: default routing: " + AudioRoutingController.this.getAudioRouteDesc(AudioRoutingController.this.mDefaultRouting) + ", current routing:" + AudioRoutingController.this.getAudioRouteDesc(AudioRoutingController.this.mCurrentRouting));
        }

        public int getState() {
            return 1;
        }

        public void onEvent(int i, int i2) {
            boolean z = true;
            Logging.d(AudioRoutingController.TAG, "StartState: onEvent:" + i + ", info:" + i2);
            AudioRoutingController audioRoutingController;
            switch (i) {
                case 1:
                    AudioRoutingController.this.mHeadsetType = i2;
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 < 0) {
                        z = false;
                    }
                    audioRoutingController.mIsWiredHeadsetPlugged = z;
                    if (!AudioRoutingController.this.mPhoneInCall && !AudioRoutingController.this.mIsBTHeadsetPlugged) {
                        if (!AudioRoutingController.this.mIsWiredHeadsetPlugged || AudioRoutingController.this.mCurrentRouting == i2) {
                            AudioRoutingController.this.resetAudioRouting();
                            return;
                        } else {
                            AudioRoutingController.this.doSetAudioOutputRouting(i2);
                            return;
                        }
                    }
                    return;
                case 2:
                    if (i2 != 0 || AudioRoutingController.this.mIsBTHeadsetPlugged) {
                        audioRoutingController = AudioRoutingController.this;
                        if (i2 != 1) {
                            z = false;
                        }
                        audioRoutingController.mIsBTHeadsetPlugged = z;
                        if (!AudioRoutingController.this.mPhoneInCall) {
                            if (AudioRoutingController.this.mIsBTHeadsetPlugged) {
                                AudioRoutingController.this.doSetAudioOutputRouting(5);
                                return;
                            } else {
                                AudioRoutingController.this.resetAudioRouting();
                                return;
                            }
                        }
                        return;
                    }
                    return;
                case 3:
                    AudioRoutingController.this.mBtScoState = i2 == 1 ? 1 : 2;
                    if (!AudioRoutingController.this.mPhoneInCall) {
                        AudioRoutingController.this.getAudioManager();
                        audioRoutingController = AudioRoutingController.this;
                        if (i2 != 1) {
                            z = false;
                        }
                        audioRoutingController.checkBtScoState(z);
                        return;
                    }
                    return;
                case 11:
                    AudioRoutingController.this.mForceSpeakerphone = i2;
                    if (!AudioRoutingController.this.mPhoneInCall) {
                        AudioRoutingController.this.resetAudioRouting();
                        return;
                    }
                    return;
                case 21:
                    AudioRoutingController.this.mEngineRole = i2;
                    if (!AudioRoutingController.this.mPhoneInCall) {
                        AudioRoutingController.this.updateBluetoothSco(AudioRoutingController.this.mCurrentRouting);
                        return;
                    }
                    return;
                case 22:
                    Logging.i(AudioRoutingController.TAG, "phone state changed: " + i2);
                    audioRoutingController = AudioRoutingController.this;
                    if (i2 <= 0) {
                        z = false;
                    }
                    audioRoutingController.mPhoneInCall = z;
                    if (i2 == 0) {
                        AudioRoutingController.this.resetAudioRouting();
                        return;
                    }
                    return;
                default:
                    super.onEvent(i, i2);
                    return;
            }
        }
    }

    private class ControllerStopState extends ControllerBaseState {
        public ControllerStopState() {
            super();
            AudioRoutingController.this.cancelTimer();
            AudioRoutingController.this.mUserOverrideDefault = false;
            AudioRoutingController.this.mForceSpeakerphone = -1;
            AudioRoutingController.this.mCurrentRouting = -1;
            AudioRoutingController.this.mDefaultRouting = -1;
            AudioRoutingController.this.mForceSpeakerphone = -1;
            AudioRoutingController.this.mScoConnectionAttemps = 0;
            Logging.i(AudioRoutingController.TAG, "Monitor stopped");
        }

        public int getState() {
            return 2;
        }

        public void onEvent(int i, int i2) {
            Logging.d(AudioRoutingController.TAG, "StopState: onEvent:" + i + ", info:" + i2);
            try {
                AudioManager access$2200 = AudioRoutingController.this.getAudioManager();
                switch (i) {
                    case 11:
                        access$2200.setSpeakerphoneOn(i2 == 1);
                        AudioRoutingController.this.mCurrentRouting = i2 == 1 ? 3 : -1;
                        AudioRoutingController.this.mForceSpeakerphone = i2;
                        AudioRoutingController.this.notifyAudioRoutingChanged(AudioRoutingController.this.queryCurrentAudioRouting());
                        return;
                    default:
                        super.onEvent(i, i2);
                        return;
                }
            } catch (Throwable e) {
                Logging.e(AudioRoutingController.TAG, "onEvent: Exception ", e);
            }
        }
    }

    private class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            AudioRoutingController.this.mState.onEvent(message.what, message.arg1);
        }
    }

    private class HeadsetBroadcastReceiver extends BroadcastReceiver {
        private HeadsetBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("android.intent.action.HEADSET_PLUG") && intent.hasExtra("state")) {
                int intExtra = intent.getIntExtra("state", -1);
                if (intExtra == 1) {
                    if (intent.getIntExtra("microphone", -1) == 1) {
                        Logging.i(AudioRoutingController.TAG, "Headset w/ mic connected");
                        AudioRoutingController.this.sendEvent(1, 0);
                        return;
                    }
                    Logging.i(AudioRoutingController.TAG, "Headset w/o mic connected");
                    AudioRoutingController.this.sendEvent(1, 2);
                } else if (intExtra == 0) {
                    Logging.i(AudioRoutingController.TAG, "Headset disconnected");
                    AudioRoutingController.this.sendEvent(1, -1);
                } else {
                    Logging.i(AudioRoutingController.TAG, "Headset unknown event detected, state=" + intExtra);
                }
            }
        }
    }

    private void startTimer() {
        Logging.d(TAG, "start bluetooth timer");
        this.mEventHandler.postDelayed(this.bluetoothTimeoutRunnable, 1500);
    }

    private void cancelTimer() {
        Logging.d(TAG, "cancel bluetooth timer");
        this.mEventHandler.removeCallbacks(this.bluetoothTimeoutRunnable);
    }

    private ControllerState changeState(int i) {
        if (i == 2) {
            return new ControllerStopState();
        }
        if (i == 1) {
            return new ControllerStartState();
        }
        return new ControllerErrorState();
    }

    public AudioRoutingController(Context context, AudioRoutingListener audioRoutingListener) {
        this.mContext = new WeakReference(context);
        this.mListener = new WeakReference(audioRoutingListener);
    }

    public int initialize() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            Logging.e(TAG, "context has been GCed");
            return -1;
        }
        AudioManager audioManager = getAudioManager();
        if (audioManager == null) {
            Logging.e(TAG, "invalid context: can't get AudioManager");
            return -1;
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new EventHandler(myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        if (this.mHeadsetReceiver == null) {
            this.mHeadsetReceiver = new HeadsetBroadcastReceiver();
        }
        this.mIsWiredHeadsetPlugged = audioManager.isWiredHeadsetOn();
        this.mState = changeState(2);
        Logging.i(TAG, "Headset setup: Plugged = " + this.mIsWiredHeadsetPlugged);
        context.registerReceiver(this.mHeadsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        if (VERSION.SDK_INT >= 11 || context.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            if (this.mBTHeadsetListener != null) {
                Logging.w(TAG, "Bluetooth service Listener already been initialized");
            } else {
                try {
                    this.mBTHeadsetListener = new ServiceListener() {
                        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                            if (i == 1) {
                                Logging.i(AudioRoutingController.TAG, "on BT service connected: " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + bluetoothProfile);
                                AudioRoutingController.this.mBTHeadset = (BluetoothHeadset) bluetoothProfile;
                            }
                        }

                        public void onServiceDisconnected(int i) {
                            if (i == 1) {
                                Logging.i(AudioRoutingController.TAG, "on BT service disconnected: " + i);
                                AudioRoutingController.this.cancelTimer();
                                AudioRoutingController.this.mBTHeadset = null;
                            }
                        }
                    };
                } catch (Exception e) {
                    Logging.e(TAG, "initialize failed: unable to create BluetoothProfile.ServiceListener, err=" + e.getMessage());
                }
            }
            if (hasPermission(context, "android.permission.BLUETOOTH")) {
                try {
                    this.mBTHeadsetReceiver = new BTHeadsetBroadcastReceiver();
                } catch (Exception e2) {
                    Logging.e(TAG, "unable to create BluetoothHeadsetBroadcastReceiver, err:" + e2.getMessage());
                }
                this.mBTAdapter = BluetoothAdapter.getDefaultAdapter();
                if (this.mBTAdapter != null) {
                    this.mBTAdapter.getProfileProxy(context, this.mBTHeadsetListener, 1);
                    if (2 == this.mBTAdapter.getProfileConnectionState(1)) {
                        this.mIsBTHeadsetPlugged = true;
                    }
                    Logging.i(TAG, "BT headset setup: BTHeadsetPlugged = " + this.mIsBTHeadsetPlugged);
                    IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                    intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
                    intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
                    intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
                    Intent registerReceiver = context.registerReceiver(this.mBTHeadsetReceiver, intentFilter);
                    if (registerReceiver != null && registerReceiver.getAction().equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
                        switch (registerReceiver.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0)) {
                            case 1:
                                Logging.i(TAG, "Bluetooth SCO device connected");
                                this.mBtScoState = 1;
                                break;
                            default:
                                Logging.i(TAG, "Bluetooth SCO device unconnected");
                                this.mBtScoState = 3;
                                break;
                        }
                    }
                    return 0;
                }
                Logging.e(TAG, "initialize: failed to get bluetooth adapter!!");
                return 0;
            }
            Logging.w(TAG, "lacks BLUETOOTH permission");
            return 0;
        }
        Logging.w(TAG, "do not support BT monitoring on this device");
        return 0;
    }

    private void clearBTResource() {
        if (this.mBTAdapter != null) {
            this.mBTAdapter.closeProfileProxy(1, this.mBTHeadset);
            this.mBTAdapter = null;
        }
    }

    public void uninitialize() {
        clearBTResource();
        Context context = (Context) this.mContext.get();
        if (context != null) {
            if (this.mHeadsetReceiver != null) {
                context.unregisterReceiver(this.mHeadsetReceiver);
            }
            if (this.mBTHeadsetReceiver != null) {
                context.unregisterReceiver(this.mBTHeadsetReceiver);
            }
        }
        this.mHeadsetReceiver = null;
        this.mBTHeadsetReceiver = null;
    }

    public void startMonitoring() {
        this.mState.setState(1);
    }

    public void stopMonitoring() {
        this.mState.setState(2);
    }

    public void sendEvent(int i, int i2) {
        Logging.d(TAG, "sendEvent: [" + i + "], extra arg:" + i2);
        if (this.mEventHandler != null) {
            this.mEventHandler.sendMessage(this.mEventHandler.obtainMessage(i, i2, 0));
        }
    }

    private boolean isAudioOnly() {
        return this.mVideoDisabled || (this.mMuteLocal && this.mMuteRemotes);
    }

    private AudioManager getAudioManager() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return null;
        }
        return (AudioManager) context.getSystemService("audio");
    }

    private void notifyAudioRoutingChanged(int i) {
        AudioRoutingListener audioRoutingListener = (AudioRoutingListener) this.mListener.get();
        if (audioRoutingListener != null) {
            audioRoutingListener.onAudioRoutingChanged(i);
        } else {
            Logging.w(TAG, "failed to get audio routing listener");
        }
    }

    private int doSetAudioOutputRouting(int i) {
        Logging.i(TAG, "set audio output routing from " + getAudioRouteDesc(this.mCurrentRouting) + "to: " + getAudioRouteDesc(i));
        try {
            getAudioManager().setSpeakerphoneOn(i == 3);
            if (queryCurrentAudioRouting() != i) {
                Logging.w(TAG, "doSetAudioOutputRouting to [" + i + "] failed, actual routing:" + queryCurrentAudioRouting());
            }
            this.mCurrentRouting = i;
            updateBluetoothSco(i);
            notifyAudioRoutingChanged(i);
            Logging.i(TAG, "audio routing changed to " + getAudioRouteDesc(this.mCurrentRouting));
        } catch (Throwable e) {
            Logging.e(TAG, "set audio output routing failed:", e);
        }
        return 0;
    }

    private int updateBluetoothSco(int i) {
        if (i == 5) {
            if (this.mEngineRole == 22) {
                this.mIsBTScoStarted = false;
                cancelTimer();
                stopBtSco();
            } else {
                this.mIsBTScoStarted = true;
                startTimer();
                startBtSco();
            }
        } else if (this.mCurrentRouting == 5 && this.mIsBTScoStarted) {
            this.mIsBTScoStarted = false;
            cancelTimer();
            stopBtSco();
        }
        return 0;
    }

    private void startBtSco() {
        AudioManager audioManager = getAudioManager();
        if (this.mBtScoState != 1 && this.mBtScoState != 0) {
            if (audioManager.isBluetoothScoOn()) {
                this.mBtScoState = 1;
                return;
            }
            this.mBtScoState = 0;
            Logging.i(TAG, "opening bt sco");
            this.mScoConnectionAttemps++;
            audioManager.startBluetoothSco();
        }
    }

    private void stopBtSco() {
        AudioManager audioManager = getAudioManager();
        if (this.mBtScoState != 1 && this.mBtScoState != 0) {
            return;
        }
        if (audioManager.isBluetoothScoOn()) {
            Logging.i(TAG, "stop bt sco");
            this.mBtScoState = 2;
            audioManager.stopBluetoothSco();
            return;
        }
        this.mBtScoState = 3;
    }

    private void checkBtScoState(boolean z) {
        this.mScoConnectionAttemps = 0;
    }

    private void bluetoothTimeout() {
        AudioManager audioManager = getAudioManager();
        if (this.mBTHeadset != null) {
            List connectedDevices = this.mBTHeadset.getConnectedDevices();
            int i;
            if (connectedDevices.size() > 0) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) connectedDevices.get(0);
                if (this.mBTHeadset.isAudioConnected(bluetoothDevice)) {
                    Logging.d(TAG, "SCO connected with " + bluetoothDevice.getName());
                    i = 1;
                } else {
                    Logging.d(TAG, "SCO is not connected with " + bluetoothDevice.getName());
                    i = 0;
                }
            } else {
                Logging.w(TAG, "no bluetooth device connected.");
                i = 0;
            }
            if (this.mScoConnectionAttemps >= 4) {
                Logging.e(TAG, "start bluetooth sco timeout, actual routing:" + queryCurrentAudioRouting());
                this.mScoConnectionAttemps = 0;
                if (this.mListener.get() != null) {
                    ((AudioRoutingListener) this.mListener.get()).onAudioRoutingError(Constants.ERR_AUDIO_BT_SCO_FAILED);
                }
            } else if (this.mIsBTScoStarted && r0 == 0) {
                Logging.i(TAG, "attemps start bt sco " + this.mScoConnectionAttemps + " times");
                this.mScoConnectionAttemps++;
                startTimer();
                audioManager.startBluetoothSco();
            }
        }
    }

    private void updateDefaultRouting() {
        if (!this.mUserOverrideDefault) {
            if (isAudioOnly() && this.mDefaultRouting == 3) {
                this.mDefaultRouting = 1;
                Logging.i(TAG, "updateDefaultRouting to:" + getAudioRouteDesc(this.mDefaultRouting));
            } else if (!isAudioOnly() && this.mDefaultRouting == 1) {
                this.mDefaultRouting = 3;
            }
        }
    }

    private void resetAudioRouting() {
        int i = 1;
        if (this.mForceSpeakerphone == 1) {
            Logging.i(TAG, "reset audio routing, default routing: " + getAudioRouteDesc(this.mDefaultRouting) + ", current routing: " + getAudioRouteDesc(this.mCurrentRouting) + ", target routing: " + getAudioRouteDesc(3) + ", actual system routing:" + getAudioRouteDesc(queryCurrentAudioRouting()));
            if (this.mCurrentRouting != 3 || queryCurrentAudioRouting() != 3) {
                doSetAudioOutputRouting(3);
                return;
            }
            return;
        }
        if (this.mIsBTHeadsetPlugged) {
            i = 5;
        } else if (this.mIsWiredHeadsetPlugged) {
            i = this.mHeadsetType;
        } else if (this.mForceSpeakerphone != 0) {
            i = this.mDefaultRouting;
        }
        Logging.i(TAG, "reset audio routing, default routing: " + getAudioRouteDesc(this.mDefaultRouting) + ", current routing: " + getAudioRouteDesc(this.mCurrentRouting) + ", target routing: " + getAudioRouteDesc(i) + ", actual system routing:" + getAudioRouteDesc(queryCurrentAudioRouting()));
        if (this.mCurrentRouting != i || queryCurrentAudioRouting() != this.mCurrentRouting) {
            doSetAudioOutputRouting(i);
        }
    }

    private String getAudioRouteDesc(int i) {
        switch (i) {
            case -1:
                return "Default";
            case 0:
                return "Headset";
            case 1:
                return "Earpiece";
            case 2:
                return "HeadsetOnly";
            case 3:
                return "Speakerphone";
            case 4:
                return "Loudspeaker";
            case 5:
                return "HeadsetBluetooth";
            default:
                return "Unknown";
        }
    }

    private int queryCurrentAudioRouting() {
        AudioManager audioManager = getAudioManager();
        if (audioManager.isSpeakerphoneOn()) {
            return 3;
        }
        if (audioManager.isBluetoothScoOn() || audioManager.isBluetoothA2dpOn()) {
            return 5;
        }
        if (audioManager.isWiredHeadsetOn()) {
            return 0;
        }
        return 1;
    }

    protected boolean hasPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }
}
