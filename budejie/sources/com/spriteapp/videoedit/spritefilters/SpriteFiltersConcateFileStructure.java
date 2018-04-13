package com.spriteapp.videoedit.spritefilters;

public class SpriteFiltersConcateFileStructure {
    private int m_iOutputAudioFlag = 0;
    private int m_iOutputVideoHeight = 0;
    private int m_iOutputVideoWidth = 0;
    private String m_strInputAV1FileNamePath = null;
    private String m_strInputAV2FileNamePath = null;
    private String m_strInputAV3FileNamePath = null;
    private String m_strOutputFileNamePath = null;

    public int spriteSetInputAV1FileNamePath(String str) {
        this.m_strInputAV1FileNamePath = str;
        return 0;
    }

    public int spriteSetInputAV2FileNamePath(String str) {
        this.m_strInputAV2FileNamePath = str;
        return 0;
    }

    public int spriteSetInputAV3FileNamePath(String str) {
        this.m_strInputAV3FileNamePath = str;
        return 0;
    }

    public int spriteSetOutputFileNamePath(String str) {
        this.m_strOutputFileNamePath = str;
        return 0;
    }

    public int spriteSetOutputVideoWidth(int i) {
        this.m_iOutputVideoWidth = i;
        return 0;
    }

    public int spriteGetOutputVideoWidth() {
        return this.m_iOutputVideoWidth;
    }

    public int spriteSetOutputVideoHeight(int i) {
        this.m_iOutputVideoHeight = i;
        return 0;
    }

    public int spriteGetOutputVideoHeight() {
        return this.m_iOutputVideoHeight;
    }

    public int spriteSetOutputAudioFlag(int i) {
        this.m_iOutputAudioFlag = i;
        return 0;
    }

    public int spriteGetOutputAudioFlag() {
        return this.m_iOutputAudioFlag;
    }
}
