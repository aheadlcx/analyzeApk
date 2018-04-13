package com.spriteapp.videoedit.spritefilters;

public class SpriteFiltersInfoStructure {
    private float m_fEndTime = 0.0f;
    private float m_fStartTime = 0.0f;
    private int m_iIsAudio = 0;
    private int m_iIsWater = 0;
    private int m_iOutputVideoHeight = 0;
    private int m_iOutputVideoWidth = 0;
    private int m_iVideoCropHeight = 0;
    private int m_iVideoCropWidth = 0;
    private int m_iVideoCropX = 0;
    private int m_iVideoCropY = 0;
    private int m_iVideoHorizontallyFlip = 0;
    private int m_iVideoIsCrop = 0;
    private int m_iVideoTranspose = 0;
    private int m_iWaterX = 0;
    private int m_iWaterY = 0;
    private String m_strInputAudioFileNamePath = null;
    private String m_strInputSRTFileNamePath = null;
    private String m_strInputVideoFileNamePath = null;
    private String m_strOutputFileNamePath = null;
    private String m_strTTFFileNamePath = null;
    private String m_strWaterFileNamePath = null;

    public int spriteSetInputVideoFileNamePath(String str) {
        this.m_strInputVideoFileNamePath = str;
        return 0;
    }

    public int spriteSetInputAudioFileNamePath(String str) {
        this.m_strInputAudioFileNamePath = str;
        return 0;
    }

    public int spriteSetInputSRTFileNamePath(String str) {
        this.m_strInputSRTFileNamePath = str;
        return 0;
    }

    public int spriteSetTTFFileNamePath(String str) {
        this.m_strTTFFileNamePath = str;
        return 0;
    }

    public int spriteSetOutputFileNamePath(String str) {
        this.m_strOutputFileNamePath = str;
        return 0;
    }

    public int spriteSetVideoIsCrop(int i) {
        this.m_iVideoIsCrop = i;
        return 0;
    }

    public int spriteSetVideoCropX(int i) {
        this.m_iVideoCropX = i;
        return 0;
    }

    public int spriteSetVideoCropY(int i) {
        this.m_iVideoCropY = i;
        return 0;
    }

    public int spriteSetVideoCropWidth(int i) {
        this.m_iVideoCropWidth = i;
        return 0;
    }

    public int spriteSetVideoCropHeight(int i) {
        this.m_iVideoCropHeight = i;
        return 0;
    }

    public int spriteSetVideoHorizontallyFlip(int i) {
        this.m_iVideoHorizontallyFlip = i;
        return 0;
    }

    public int spriteSetVideoTranspose(int i) {
        this.m_iVideoTranspose = i;
        return 0;
    }

    public int spriteSetOutputVideoWidth(int i) {
        this.m_iOutputVideoWidth = i;
        return 0;
    }

    public int spriteSetOutputVideoHeight(int i) {
        this.m_iOutputVideoHeight = i;
        return 0;
    }

    public int spriteSetIsWater(int i) {
        this.m_iIsWater = i;
        return 0;
    }

    public int spriteSetWaterFileNamePath(String str) {
        this.m_strWaterFileNamePath = str;
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

    public int spriteSetStartTime(float f) {
        this.m_fStartTime = f;
        return 0;
    }

    public int spriteSetEndTime(float f) {
        this.m_fEndTime = f;
        return 0;
    }

    public int spriteSetIsAudio(int i) {
        this.m_iIsAudio = i;
        return 0;
    }
}
