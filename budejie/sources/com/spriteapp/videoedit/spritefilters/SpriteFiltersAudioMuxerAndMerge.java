package com.spriteapp.videoedit.spritefilters;

public class SpriteFiltersAudioMuxerAndMerge {
    private float m_fEndTime = 0.0f;
    private float m_fStartTime = 0.0f;
    private int m_iFrameRate = 0;
    private int m_iIsWater = 0;
    private int m_iOutputVideoHeight = 0;
    private int m_iOutputVideoWidth = 0;
    private int m_iWaterX = 0;
    private int m_iWaterY = 0;
    private String m_strACodec = null;
    private String m_strInputAVFileNamePath = null;
    private String m_strInputAudioFileNamePath = null;
    private String m_strOutputAVFileNamePath = null;
    private String m_strVCodec = null;
    private String m_strWaterFileNamePath = null;

    public int spriteSetInputAVFileNamePath(String str) {
        this.m_strInputAVFileNamePath = str;
        return 0;
    }

    public int spriteSetInputAudioFileNamePath(String str) {
        this.m_strInputAudioFileNamePath = str;
        return 0;
    }

    public int spriteSetWaterFileNamePath(String str) {
        this.m_strWaterFileNamePath = str;
        return 0;
    }

    public int spriteSetOutputAVFileNamePath(String str) {
        this.m_strOutputAVFileNamePath = str;
        return 0;
    }

    public int spriteSetVCodec(String str) {
        this.m_strVCodec = str;
        return 0;
    }

    public int spriteSetACodec(String str) {
        this.m_strACodec = str;
        return 0;
    }

    public int spriteSetIsWater(int i) {
        this.m_iIsWater = i;
        return 0;
    }

    public int spriteSetWaterX(int i) {
        this.m_iWaterX = i;
        return 0;
    }

    public int spriteSetWaterY(int i) {
        this.m_iWaterY = i;
        return 0;
    }

    public int spriteSetFrameRate(int i) {
        this.m_iFrameRate = i;
        return 0;
    }

    public int spriteSetStartTime(float f) {
        this.m_fStartTime = f;
        return 0;
    }

    public int spriteSetEndTime(float f) {
        this.m_fEndTime = f;
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
}
