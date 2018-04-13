package com.google.tagmanager;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.analytics.containertag.proto.Serving.Resource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.LoadCallback.Failure;
import com.google.tagmanager.ResourceUtil.ExpandedResource;
import com.google.tagmanager.proto.Resource.ResourceWithMetadata;
import com.google.tagmanager.protobuf.nano.MessageNano;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class ResourceStorageImpl implements ResourceStorage {
    private static final String SAVED_RESOURCE_FILENAME_PREFIX = "resource_";
    private static final String SAVED_RESOURCE_SUB_DIR = "google_tagmanager";
    private LoadCallback<ResourceWithMetadata> mCallback;
    private final String mContainerId;
    private final Context mContext;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    ResourceStorageImpl(Context context, String str) {
        this.mContext = context;
        this.mContainerId = str;
    }

    public void setLoadCallback(LoadCallback<ResourceWithMetadata> loadCallback) {
        this.mCallback = loadCallback;
    }

    public void loadResourceFromDiskInBackground() {
        this.mExecutor.execute(new Runnable() {
            public void run() {
                ResourceStorageImpl.this.loadResourceFromDisk();
            }
        });
    }

    @VisibleForTesting
    void loadResourceFromDisk() {
        if (this.mCallback == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.mCallback.startLoad();
        Log.v("Start loading resource from disk ...");
        if ((PreviewManager.getInstance().getPreviewMode() == PreviewMode.CONTAINER || PreviewManager.getInstance().getPreviewMode() == PreviewMode.CONTAINER_DEBUG) && this.mContainerId.equals(PreviewManager.getInstance().getContainerId())) {
            this.mCallback.onFailure(Failure.NOT_AVAILABLE);
            return;
        }
        try {
            InputStream fileInputStream = new FileInputStream(getResourceFile());
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ResourceUtil.copyStream(fileInputStream, byteArrayOutputStream);
                this.mCallback.onSuccess(ResourceWithMetadata.parseFrom(byteArrayOutputStream.toByteArray()));
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Log.w("error closing stream for reading resource from disk");
                }
            } catch (IOException e2) {
                Log.w("error reading resource from disk");
                this.mCallback.onFailure(Failure.IO_ERROR);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.w("error closing stream for reading resource from disk");
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    Log.w("error closing stream for reading resource from disk");
                }
                throw th;
            }
            Log.v("Load resource from disk finished.");
        } catch (FileNotFoundException e5) {
            Log.d("resource not on disk");
            this.mCallback.onFailure(Failure.NOT_AVAILABLE);
        }
    }

    public void saveResourceToDiskInBackground(final ResourceWithMetadata resourceWithMetadata) {
        this.mExecutor.execute(new Runnable() {
            public void run() {
                ResourceStorageImpl.this.saveResourceToDisk(resourceWithMetadata);
            }
        });
    }

    public Resource loadResourceFromContainerAsset(String str) {
        Log.v("Loading default container from " + str);
        AssetManager assets = this.mContext.getAssets();
        if (assets == null) {
            Log.e("No assets found in package");
            return null;
        }
        try {
            InputStream open = assets.open(str);
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ResourceUtil.copyStream(open, byteArrayOutputStream);
                Resource parseFrom = Resource.parseFrom(byteArrayOutputStream.toByteArray());
                Log.v("Parsed default container: " + parseFrom);
                try {
                    open.close();
                } catch (IOException e) {
                }
                return parseFrom;
            } catch (IOException e2) {
                Log.w("Error when parsing: " + str);
                try {
                    open.close();
                    return null;
                } catch (IOException e3) {
                    return null;
                }
            } catch (Throwable th) {
                try {
                    open.close();
                } catch (IOException e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            Log.w("No asset file: " + str + " found.");
            return null;
        }
    }

    public ExpandedResource loadExpandedResourceFromJsonAsset(String str) {
        InputStream open;
        Throwable th;
        InputStream inputStream;
        Object obj;
        ExpandedResource expandedResource = null;
        Log.v("loading default container from " + str);
        AssetManager assets = this.mContext.getAssets();
        if (assets == null) {
            Log.w("Looking for default JSON container in package, but no assets were found.");
        } else {
            try {
                open = assets.open(str);
                try {
                    expandedResource = JsonUtils.expandedResourceFromJsonString(stringFromInputStream(open));
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                    try {
                        Log.w("No asset file: " + str + " found (or errors reading it).");
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e3) {
                            }
                        }
                        return expandedResource;
                    } catch (Throwable th2) {
                        th = th2;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e4) {
                            }
                        }
                        throw th;
                    }
                } catch (JSONException e5) {
                    JSONException jSONException = e5;
                    inputStream = open;
                    JSONException jSONException2 = jSONException;
                    try {
                        Log.w("Error parsing JSON file" + str + " : " + obj);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        return expandedResource;
                    } catch (Throwable th3) {
                        th = th3;
                        open = inputStream;
                        if (open != null) {
                            open.close();
                        }
                        throw th;
                    }
                }
            } catch (IOException e7) {
                open = expandedResource;
                Log.w("No asset file: " + str + " found (or errors reading it).");
                if (open != null) {
                    open.close();
                }
                return expandedResource;
            } catch (JSONException e8) {
                obj = e8;
                inputStream = expandedResource;
                Log.w("Error parsing JSON file" + str + " : " + obj);
                if (inputStream != null) {
                    inputStream.close();
                }
                return expandedResource;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                open = expandedResource;
                th = th5;
                if (open != null) {
                    open.close();
                }
                throw th;
            }
        }
        return expandedResource;
    }

    public synchronized void close() {
        this.mExecutor.shutdown();
    }

    @VisibleForTesting
    boolean saveResourceToDisk(ResourceWithMetadata resourceWithMetadata) {
        boolean z = false;
        File resourceFile = getResourceFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(resourceFile);
            try {
                fileOutputStream.write(MessageNano.toByteArray(resourceWithMetadata));
                z = true;
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.w("error closing stream for writing resource to disk");
                }
            } catch (IOException e2) {
                Log.w("Error writing resource to disk. Removing resource from disk.");
                resourceFile.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    Log.w("error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    Log.w("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            Log.e("Error opening resource file for writing");
        }
        return z;
    }

    @VisibleForTesting
    File getResourceFile() {
        return new File(this.mContext.getDir(SAVED_RESOURCE_SUB_DIR, 0), SAVED_RESOURCE_FILENAME_PREFIX + this.mContainerId);
    }

    private String stringFromInputStream(InputStream inputStream) throws IOException {
        Writer stringWriter = new StringWriter();
        char[] cArr = new char[1024];
        Reader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read == -1) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }
}
