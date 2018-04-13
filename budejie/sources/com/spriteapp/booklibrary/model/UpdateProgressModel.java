package com.spriteapp.booklibrary.model;

public class UpdateProgressModel {
    private int bookId;
    private int chapterId;
    private int chapterIndex;
    private int chapterTotal;

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int i) {
        this.bookId = i;
    }

    public int getChapterId() {
        return this.chapterId;
    }

    public void setChapterId(int i) {
        this.chapterId = i;
    }

    public int getChapterIndex() {
        return this.chapterIndex;
    }

    public void setChapterIndex(int i) {
        this.chapterIndex = i;
    }

    public int getChapterTotal() {
        return this.chapterTotal;
    }

    public void setChapterTotal(int i) {
        this.chapterTotal = i;
    }
}
