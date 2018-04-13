package com.spriteapp.videoedit.spritefilters;

public class SpriteFiltersGetImageStructure {
    private float m_fTotalTime = 0.0f;
    private int m_iOutImageCount = 0;
    private String m_strInputVideoFileNamePath = null;
    private String m_strOutImageName = null;
    private String m_strOutputImagePath = null;

    public int spriteSetInputVideFileNamePath(String str) {
        this.m_strInputVideoFileNamePath = str;
        return 0;
    }

    public int spriteSetOutputImagePath(String str) {
        this.m_strOutputImagePath = str;
        return 0;
    }

    public int spriteSetOutImageName(String str) {
        this.m_strOutImageName = str;
        return 0;
    }

    public int spriteSetOutImageCount(int i) {
        this.m_iOutImageCount = i;
        return 0;
    }

    public int spriteSetTotalTime(float f) {
        this.m_fTotalTime = f;
        return 0;
    }
}
