package com.sprite.ads.media;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;

public class MediaFactory {
    private static MediaAdapter create(String str, AdItem adItem, ADConfig aDConfig) {
        return (MediaAdapter) Class.forName(str).getConstructor(new Class[]{AdItem.class, ADConfig.class}).newInstance(new Object[]{adItem, aDConfig});
    }

    private static MediaPlayerControler create(String str) {
        return (MediaPlayerControler) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
    }

    public static MediaAdapter createMediaAdapter(DataSourceType dataSourceType, AdItem adItem, ADConfig aDConfig) {
        MediaAdapter mediaAdapter = null;
        try {
            switch (dataSourceType) {
                case SDK_GDT:
                    mediaAdapter = create("com.sprite.ads.third.gdt.media.GdtMediaAdapter", adItem, aDConfig);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaAdapter;
    }

    public static MediaPlayerControler createMediaPlayerControler(DataSourceType dataSourceType) {
        MediaPlayerControler mediaPlayerControler = null;
        try {
            switch (dataSourceType) {
                case SDK_GDT:
                    mediaPlayerControler = create("com.sprite.ads.third.gdt.media.GdtMediaPlayerControler");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaPlayerControler;
    }
}
