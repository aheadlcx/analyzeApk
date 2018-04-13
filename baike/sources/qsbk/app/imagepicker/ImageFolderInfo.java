package qsbk.app.imagepicker;

import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.model.ImageInfo;

public class ImageFolderInfo {
    private int a;
    private String b;
    private ArrayList<ImageInfo> c;

    public ImageFolderInfo() {
        this(0, null, new ArrayList());
    }

    public ImageFolderInfo(int i, String str) {
        this(i, str, new ArrayList());
    }

    private ImageFolderInfo(int i, String str, ArrayList<ImageInfo> arrayList) {
        this.a = i;
        this.b = str;
        this.c = arrayList;
    }

    public int getId() {
        return this.a;
    }

    public void setId(int i) {
        this.a = i;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void addImage(ImageInfo imageInfo) {
        this.c.add(imageInfo);
    }

    public void addImage(int i, ImageInfo imageInfo) {
        this.c.add(i, imageInfo);
    }

    public String getPath(int i) {
        return ((ImageInfo) this.c.get(i)).url;
    }

    public ImageInfo getById(int i) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ImageInfo imageInfo = (ImageInfo) it.next();
            if (imageInfo.id == i) {
                return imageInfo;
            }
        }
        return null;
    }

    public ImageInfo getByPath(String str) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ImageInfo imageInfo = (ImageInfo) it.next();
            if (imageInfo.url.equalsIgnoreCase(str)) {
                return imageInfo;
            }
        }
        return null;
    }

    public ImageInfo get(int i) {
        return (ImageInfo) this.c.get(i);
    }

    public int size() {
        return this.c.size();
    }

    public ArrayList<ImageInfo> list() {
        return this.c;
    }
}
