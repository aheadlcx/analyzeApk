package com.media.ffmpeg;

import java.io.File;

public class FFMpegFile {
    protected File mFile;

    protected FFMpegFile(File file) {
        this.mFile = file;
    }

    public File getFile() {
        return this.mFile;
    }

    public boolean exists() {
        return this.mFile.exists();
    }

    public void delete() {
        this.mFile.delete();
        this.mFile = null;
    }
}
