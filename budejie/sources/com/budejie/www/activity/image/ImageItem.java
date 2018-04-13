package com.budejie.www.activity.image;

import java.io.Serializable;

public class ImageItem implements Serializable {
    public String imageId;
    public String imagePath;
    public boolean isSelected = false;
    public String mimeType;
    public long modifiedTime;
    public String selectedNum = "";
    public String thumbnailPath;
    public String type;
}
