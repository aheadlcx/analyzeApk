package io.agora.rtc;

public interface IAudioEffectManager {
    double getEffectsVolume();

    int pauseAllEffects();

    int pauseEffect(int i);

    int playEffect(int i, String str, boolean z, double d, double d2, double d3);

    int preloadEffect(int i, String str);

    int resumeAllEffects();

    int resumeEffect(int i);

    int setEffectsVolume(double d);

    int stopAllEffects();

    int stopEffect(int i);

    int unloadEffect(int i);
}
