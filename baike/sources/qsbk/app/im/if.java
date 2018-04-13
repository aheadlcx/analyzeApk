package qsbk.app.im;

class if implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ MqttChatBaseActivity c;

    if(MqttChatBaseActivity mqttChatBaseActivity, long j, int i) {
        this.c = mqttChatBaseActivity;
        this.a = j;
        this.b = i;
    }

    public void run() {
        this.c.b.onMsgStateChange(this.a, this.b);
    }
}
