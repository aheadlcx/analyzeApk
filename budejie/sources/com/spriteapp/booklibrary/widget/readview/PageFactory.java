package com.spriteapp.booklibrary.widget.readview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.widget.ProgressBar;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.b.a;
import com.spriteapp.booklibrary.d.d;
import com.spriteapp.booklibrary.e.c;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.model.response.SubscriberContent;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.EncryptUtils;
import com.spriteapp.booklibrary.util.MapUtil;
import com.spriteapp.booklibrary.util.NetworkUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class PageFactory {
    private static final int LEFT_PLUS_MARGIN = 5;
    private static final String TAG = "PageFactory";
    private int battery;
    private Bitmap batteryBitmap;
    private String bookId;
    private List<BookChapterResponse> chaptersList;
    private int curBeginPos;
    private int curEndPos;
    private int currentChapter;
    private int currentPage;
    private DecimalFormat decimalFormat;
    private OnReadStateChangeListener listener;
    private int mBatteryWidth;
    private Bitmap mBookPageBg;
    private d mContentDb;
    private Context mContext;
    private String mCurrentContent;
    private int mFontSize;
    private int mHeight;
    private int mLineSpace;
    private Vector<String> mLines;
    private int mPageLineCount;
    private Map<Integer, Integer> mPageMap;
    private Paint mPaint;
    private a mProgressCallback;
    private Paint mTitlePaint;
    private int mTotalPage;
    private int mVisibleHeight;
    private int mVisibleWidth;
    private int mWidth;
    private int marginHeight;
    private int marginWidth;
    private int mbBufferLen;
    private int percentLen;
    private Rect rectF;
    private int tempBeginPos;
    private int tempChapter;
    private int tempEndPos;
    private String time;

    public PageFactory(Context context, String str, List<BookChapterResponse> list) {
        this(context, ScreenUtil.getScreenWidth(), ScreenUtil.getScreenHeight(), c.a().b(), str, list);
    }

    public PageFactory(Context context, int i, int i2, int i3, String str, List<BookChapterResponse> list) {
        this.curEndPos = 0;
        this.curBeginPos = 0;
        this.mLines = new Vector();
        this.decimalFormat = new DecimalFormat("#0.00");
        this.percentLen = 0;
        this.battery = 40;
        this.currentPage = 1;
        this.mContext = context;
        this.mWidth = i;
        this.mHeight = i2;
        this.mFontSize = i3;
        this.mLineSpace = (this.mFontSize / 17) * 12;
        this.marginWidth = ScreenUtil.dpToPxInt(14.0f);
        this.marginHeight = ScreenUtil.dpToPxInt(66.0f);
        this.mVisibleHeight = (this.mHeight - (this.marginHeight * 2)) + ScreenUtil.dpToPxInt(25.0f);
        this.mVisibleWidth = this.mWidth - (this.marginWidth * 2);
        this.mPageLineCount = this.mVisibleHeight / (this.mFontSize + this.mLineSpace);
        this.rectF = new Rect(0, 0, this.mWidth, this.mHeight);
        this.mPaint = new Paint(1);
        this.mPaint.setTextSize((float) this.mFontSize);
        this.mPaint.setColor(-16777216);
        this.mTitlePaint = new Paint(1);
        this.mTitlePaint.setTextSize((float) ScreenUtil.dpToPxInt(11.0f));
        this.mTitlePaint.setColor(AppUtil.getAppContext().getResources().getColor(com.spriteapp.booklibrary.a.a.book_reader_read_title_color));
        this.percentLen = (int) this.mTitlePaint.measureText("00.00%");
        this.bookId = str;
        this.chaptersList = list;
        this.time = new SimpleDateFormat("HH:mm").format(new Date());
        this.mContentDb = new d(this.mContext);
        this.mBatteryWidth = ScreenUtil.dpToPxInt(20.0f);
    }

    public int openBook(int i, int[] iArr) {
        this.currentChapter = i;
        SubscriberContent a = this.mContentDb.a(Integer.valueOf(this.bookId).intValue(), i);
        if (a == null) {
            return 0;
        }
        this.mCurrentContent = EncryptUtils.decrypt(a.getChapter_content(), a.getChapter_content_key());
        this.mbBufferLen = this.mCurrentContent.length();
        this.curBeginPos = iArr[0];
        this.curEndPos = iArr[1];
        setChapterTotalPage();
        onChapterChanged(i);
        this.mLines.clear();
        return 1;
    }

    public synchronized void onDraw(Canvas canvas) {
        if (this.mLines.size() == 0) {
            this.curEndPos = this.curBeginPos;
            this.mLines = pageDown();
        }
        if (this.mLines.size() > 0) {
            int i = this.marginHeight;
            if (this.mBookPageBg != null) {
                canvas.drawBitmap(this.mBookPageBg, null, this.rectF, null);
            } else {
                canvas.drawColor(AppUtil.getAppContext().getResources().getColor(com.spriteapp.booklibrary.a.a.book_reader_read_page_default_background));
            }
            if (!CollectionUtil.isEmpty(this.chaptersList)) {
                for (BookChapterResponse bookChapterResponse : this.chaptersList) {
                    if (bookChapterResponse.getChapter_id() == this.currentChapter) {
                        canvas.drawText(bookChapterResponse.getChapter_title(), (float) (this.marginWidth + ScreenUtil.dpToPxInt(5.0f)), ScreenUtil.dpToPx(40.0f), this.mTitlePaint);
                        break;
                    }
                }
            }
            int dpToPx = (int) (((float) i) + ScreenUtil.dpToPx(3.0f));
            Iterator it = this.mLines.iterator();
            i = dpToPx;
            while (it.hasNext()) {
                String str = (String) it.next();
                i += this.mLineSpace;
                if (str.endsWith("@")) {
                    canvas.drawText(str.substring(0, str.length() - 1), (float) (this.marginWidth + ScreenUtil.dpToPxInt(5.0f)), (float) i, this.mPaint);
                    dpToPx = this.mLineSpace + i;
                } else {
                    canvas.drawText(str, (float) (this.marginWidth + ScreenUtil.dpToPxInt(5.0f)), (float) i, this.mPaint);
                    dpToPx = i;
                }
                i = dpToPx + this.mFontSize;
            }
            if (this.batteryBitmap != null) {
                canvas.drawBitmap(this.batteryBitmap, (float) (this.marginWidth + ScreenUtil.dpToPxInt(5.0f)), (float) (this.mHeight - ScreenUtil.dpToPxInt(20.0f)), this.mTitlePaint);
            }
            float f = (((float) this.curEndPos) * 100.0f) / ((float) this.mbBufferLen);
            if (this.mProgressCallback != null) {
                this.mProgressCallback.sendProgress(f);
            }
            canvas.drawText(this.decimalFormat.format((double) f) + "%", (float) ((this.mWidth - this.marginWidth) - this.percentLen), (float) (this.mHeight - ScreenUtil.dpToPxInt(12.0f)), this.mTitlePaint);
            canvas.drawText(this.time, (float) ((this.marginWidth + ScreenUtil.dpToPxInt(10.0f)) + this.mBatteryWidth), (float) (this.mHeight - ScreenUtil.dpToPxInt(12.0f)), this.mTitlePaint);
            c.a().a(this.bookId, this.currentChapter, this.curBeginPos, this.curEndPos);
        }
    }

    public void setProgressCallback(a aVar) {
        this.mProgressCallback = aVar;
    }

    private void pageUp() {
        Vector vector = new Vector();
        this.mPageLineCount = this.mVisibleHeight / (this.mFontSize + this.mLineSpace);
        int i = 0;
        while (vector.size() < this.mPageLineCount && this.curBeginPos > 0) {
            Collection vector2 = new Vector();
            String readUpParagraph = readUpParagraph(this.curBeginPos);
            this.curBeginPos -= readUpParagraph.length();
            while (readUpParagraph.length() > 0) {
                int breakText = this.mPaint.breakText(readUpParagraph, true, (float) this.mVisibleWidth, null);
                vector2.add(readUpParagraph.substring(0, breakText));
                readUpParagraph = readUpParagraph.substring(breakText);
            }
            vector.addAll(0, vector2);
            while (vector.size() > this.mPageLineCount) {
                this.curBeginPos = ((String) vector.get(0)).length() + this.curBeginPos;
                vector.remove(0);
            }
            this.curEndPos = this.curBeginPos;
            int i2 = this.mLineSpace + i;
            this.mPageLineCount = (this.mVisibleHeight - i2) / (this.mFontSize + this.mLineSpace);
            i = i2;
        }
    }

    private Vector<String> pageDown() {
        Vector<String> vector = new Vector();
        this.mPageLineCount = this.mVisibleHeight / (this.mFontSize + this.mLineSpace);
        int i = 0;
        while (vector.size() < this.mPageLineCount && this.curEndPos < this.mbBufferLen) {
            String str;
            String readNextParagraph = readNextParagraph(this.curEndPos);
            this.curEndPos += readNextParagraph.length();
            boolean isEmpty = StringUtil.isEmpty(readNextParagraph);
            while (readNextParagraph.length() > 0) {
                int breakText = this.mPaint.breakText(readNextParagraph, true, (float) this.mVisibleWidth, null);
                vector.add(readNextParagraph.substring(0, breakText));
                readNextParagraph = readNextParagraph.substring(breakText);
                if (vector.size() >= this.mPageLineCount) {
                    str = readNextParagraph;
                    break;
                }
            }
            str = readNextParagraph;
            if (!(CollectionUtil.isEmpty(vector) || isEmpty)) {
                vector.set(vector.size() - 1, ((String) vector.get(vector.size() - 1)) + "@");
            }
            if (str.length() != 0) {
                this.curEndPos -= str.length();
            }
            int i2 = this.mLineSpace + i;
            this.mPageLineCount = (this.mVisibleHeight - i2) / (this.mFontSize + this.mLineSpace);
            i = i2;
        }
        return vector;
    }

    public int getChapterTotalPage() {
        return this.mTotalPage;
    }

    public int setChapterTotalPage() {
        if (this.mPageMap == null) {
            this.mPageMap = new LinkedHashMap();
        }
        this.mPageMap.clear();
        Vector vector = new Vector();
        this.mTotalPage = 0;
        int i;
        for (int i2 = 0; i2 < this.mbBufferLen; i2 = i) {
            this.mPageLineCount = this.mVisibleHeight / (this.mFontSize + this.mLineSpace);
            int i3 = 0;
            i = i2;
            while (vector.size() < this.mPageLineCount && i < this.mbBufferLen) {
                int breakText;
                String readNextParagraph = readNextParagraph(i);
                int length = readNextParagraph.length() + i;
                String str = readNextParagraph;
                while (str.length() > 0) {
                    breakText = this.mPaint.breakText(str, true, (float) this.mVisibleWidth, null);
                    vector.add(str.substring(0, breakText));
                    str = str.substring(breakText);
                    if (vector.size() >= this.mPageLineCount) {
                        break;
                    }
                }
                if (str.length() != 0) {
                    breakText = length - str.length();
                } else {
                    breakText = length;
                }
                i = this.mLineSpace + i3;
                this.mPageLineCount = (this.mVisibleHeight - i) / (this.mFontSize + this.mLineSpace);
                i3 = i;
                i = breakText;
            }
            if (i < this.mbBufferLen) {
                vector.clear();
            }
            this.mPageMap.put(Integer.valueOf(this.mTotalPage), Integer.valueOf(i2));
            this.mTotalPage++;
        }
        return this.mTotalPage;
    }

    public float getCurrentProgress() {
        return (((float) this.curEndPos) * 100.0f) / ((float) this.mbBufferLen);
    }

    public void setSelectPage(int i) {
        if (!MapUtil.isEmpty(this.mPageMap)) {
            if (i == this.mPageMap.size()) {
                i--;
            }
            if (i >= 0 && i < this.mPageMap.size() && this.mPageMap.containsKey(Integer.valueOf(i))) {
                this.curBeginPos = ((Integer) this.mPageMap.get(Integer.valueOf(i))).intValue();
                this.mLines.clear();
            }
        }
    }

    private String readNextParagraph(int i) {
        String str = "";
        while (i < this.mbBufferLen) {
            char charAt = this.mCurrentContent.charAt(i);
            str = str + String.valueOf(charAt);
            i++;
            if ("\n".equals(String.valueOf(charAt)) && !StringUtil.isEmpty(str)) {
                break;
            }
        }
        return str;
    }

    private String readUpParagraph(int i) {
        String str = "";
        int i2 = i - 1;
        while (i2 >= 0) {
            char charAt = this.mCurrentContent.charAt(i2);
            str = str + String.valueOf(charAt);
            i2--;
            if ("\n".equals(String.valueOf(charAt)) && i2 != i - 1) {
                break;
            }
        }
        return str;
    }

    public boolean hasNextPage() {
        if (CollectionUtil.isEmpty(this.chaptersList)) {
            return false;
        }
        boolean z = ((BookChapterResponse) this.chaptersList.get(this.chaptersList.size() + -1)).getChapter_id() != this.currentChapter || this.curEndPos < this.mbBufferLen;
        return z;
    }

    public boolean hasPrePage() {
        if (CollectionUtil.isEmpty(this.chaptersList)) {
            return false;
        }
        boolean z;
        BookChapterResponse bookChapterResponse = (BookChapterResponse) this.chaptersList.get(0);
        if (bookChapterResponse.getChapter_id() != this.currentChapter || (bookChapterResponse.getChapter_id() == this.currentChapter && this.curBeginPos > 0)) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public BookStatus nextPage() {
        if (!hasNextPage()) {
            return BookStatus.NO_NEXT_PAGE;
        }
        int i;
        this.tempChapter = this.currentChapter;
        this.tempBeginPos = this.curBeginPos;
        this.tempEndPos = this.curEndPos;
        if (this.curEndPos < this.mbBufferLen || CollectionUtil.isEmpty(this.chaptersList)) {
            this.curBeginPos = this.curEndPos;
        } else {
            int size = this.chaptersList.size();
            i = 0;
            while (i < size) {
                if (((BookChapterResponse) this.chaptersList.get(i)).getChapter_id() == this.currentChapter && i + 1 < size) {
                    this.currentChapter = ((BookChapterResponse) this.chaptersList.get(i + 1)).getChapter_id();
                    break;
                }
                i++;
            }
            if (openBook(this.currentChapter, new int[]{0, 0}) == 0) {
                onLoadChapterFailure(this.currentChapter);
                if (!NetworkUtil.isAvailable(this.mContext)) {
                    this.currentChapter = this.tempChapter;
                }
                this.curBeginPos = this.tempBeginPos;
                this.curEndPos = this.tempEndPos;
                return BookStatus.NEXT_CHAPTER_LOAD_FAILURE;
            }
            this.currentPage = 0;
        }
        this.mLines.clear();
        this.mLines = pageDown();
        int i2 = this.currentChapter;
        i = this.currentPage + 1;
        this.currentPage = i;
        onPageChanged(i2, i);
        return BookStatus.LOAD_SUCCESS;
    }

    public void setCurrentChapter() {
        this.currentChapter = this.tempChapter;
    }

    public BookStatus prePage() {
        if (!hasPrePage()) {
            return BookStatus.NO_PRE_PAGE;
        }
        this.tempChapter = this.currentChapter;
        this.tempBeginPos = this.curBeginPos;
        this.tempEndPos = this.curEndPos;
        if (this.curBeginPos > 0 || CollectionUtil.isEmpty(this.chaptersList)) {
            int intValue;
            this.mLines.clear();
            if (this.mPageMap.containsValue(Integer.valueOf(this.curBeginPos))) {
                for (Entry entry : this.mPageMap.entrySet()) {
                    if (((Integer) entry.getValue()).intValue() == this.curBeginPos) {
                        intValue = ((Integer) entry.getKey()).intValue();
                        if (this.mPageMap.containsKey(Integer.valueOf(intValue - 1))) {
                            this.curEndPos = ((Integer) this.mPageMap.get(Integer.valueOf(intValue - 1))).intValue();
                        } else {
                            this.curEndPos = 0;
                        }
                        this.curBeginPos = this.curEndPos;
                    }
                }
            } else {
                pageUp();
            }
            this.mLines = pageDown();
            intValue = this.currentChapter;
            int i = this.currentPage - 1;
            this.currentPage = i;
            onPageChanged(intValue, i);
            return BookStatus.LOAD_SUCCESS;
        }
        int size = this.chaptersList.size();
        i = 0;
        while (i < size) {
            if (((BookChapterResponse) this.chaptersList.get(i)).getChapter_id() == this.currentChapter && i != 0) {
                this.currentChapter = ((BookChapterResponse) this.chaptersList.get(i - 1)).getChapter_id();
                break;
            }
            i++;
        }
        if (openBook(this.currentChapter, new int[]{0, 0}) == 0) {
            onLoadChapterFailure(this.currentChapter);
            if (!NetworkUtil.isAvailable(this.mContext)) {
                this.currentChapter = this.tempChapter;
            }
            return BookStatus.PRE_CHAPTER_LOAD_FAILURE;
        }
        this.mLines.clear();
        if (!MapUtil.isEmpty(this.mPageMap) && this.mPageMap.containsKey(Integer.valueOf(this.mTotalPage - 1))) {
            this.curEndPos = ((Integer) this.mPageMap.get(Integer.valueOf(this.mTotalPage - 1))).intValue();
            this.curBeginPos = this.curEndPos;
        }
        this.mLines = pageDown();
        onPageChanged(this.currentChapter, this.currentPage);
        return BookStatus.LOAD_SUCCESS;
    }

    public void cancelPage() {
        this.currentChapter = this.tempChapter;
        this.curBeginPos = this.tempBeginPos;
        this.curEndPos = this.curBeginPos;
        if (openBook(this.currentChapter, new int[]{this.curBeginPos, this.curEndPos}) == 0) {
            onLoadChapterFailure(this.currentChapter);
            return;
        }
        this.mLines.clear();
        this.mLines = pageDown();
    }

    public void setTextFont(int i) {
        this.mFontSize = i;
        this.mLineSpace = (this.mFontSize / 17) * 12;
        this.mPaint.setTextSize((float) this.mFontSize);
        this.mPageLineCount = this.mVisibleHeight / (this.mFontSize + this.mLineSpace);
        setChapterTotalPage();
        setCurrentPageBeginPos();
        nextPage();
    }

    private void setCurrentPageBeginPos() {
        if (!MapUtil.isEmpty(this.mPageMap)) {
            for (Entry entry : this.mPageMap.entrySet()) {
                if (((Integer) entry.getValue()).intValue() > this.curBeginPos) {
                    int intValue = ((Integer) entry.getKey()).intValue();
                    if (this.mPageMap.containsKey(Integer.valueOf(intValue - 1))) {
                        this.curEndPos = ((Integer) this.mPageMap.get(Integer.valueOf(intValue - 1))).intValue();
                        return;
                    } else {
                        this.curEndPos = 0;
                        return;
                    }
                } else if (((Integer) entry.getValue()).intValue() == this.curBeginPos) {
                    this.curEndPos = ((Integer) entry.getValue()).intValue();
                    return;
                }
            }
        }
    }

    public void setTextColor(int i, int i2) {
        this.mPaint.setColor(i);
        this.mTitlePaint.setColor(i2);
    }

    public void setBgBitmap(Bitmap bitmap) {
        this.mBookPageBg = bitmap;
    }

    public void setOnReadStateChangeListener(OnReadStateChangeListener onReadStateChangeListener) {
        this.listener = onReadStateChangeListener;
    }

    private void onChapterChanged(int i) {
        if (this.listener != null) {
            this.listener.onChapterChanged(i);
        }
    }

    private void onPageChanged(int i, int i2) {
        if (this.listener != null) {
            this.listener.onPageChanged(i, i2);
        }
    }

    private void onLoadChapterFailure(int i) {
        if (this.listener != null) {
            this.listener.onLoadChapterFailure(i);
        }
    }

    public void convertBatteryBitmap() {
        ProgressBar progressBar = (ProgressBar) LayoutInflater.from(this.mContext).inflate(e.layout_battery_progress, null);
        progressBar.setProgressDrawable(ContextCompat.getDrawable(this.mContext, SharedPreferencesUtil.getInstance().getBoolean("hua_xi_is_night_mode") ? com.spriteapp.booklibrary.a.c.seekbar_battery_night_bg : com.spriteapp.booklibrary.a.c.seekbar_battery_bg));
        progressBar.setProgress(this.battery);
        progressBar.setDrawingCacheEnabled(true);
        progressBar.measure(MeasureSpec.makeMeasureSpec(this.mBatteryWidth, 1073741824), MeasureSpec.makeMeasureSpec(ScreenUtil.dpToPxInt(10.0f), 1073741824));
        progressBar.layout(0, 0, progressBar.getMeasuredWidth(), progressBar.getMeasuredHeight());
        progressBar.buildDrawingCache();
        this.batteryBitmap = Bitmap.createBitmap(progressBar.getDrawingCache());
        progressBar.setDrawingCacheEnabled(false);
        progressBar.destroyDrawingCache();
    }

    public void setBattery(int i) {
        this.battery = i;
        convertBatteryBitmap();
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void recycle() {
        if (!(this.mBookPageBg == null || this.mBookPageBg.isRecycled())) {
            this.mBookPageBg.recycle();
            this.mBookPageBg = null;
        }
        if (this.batteryBitmap != null && !this.batteryBitmap.isRecycled()) {
            this.batteryBitmap.recycle();
            this.batteryBitmap = null;
        }
    }
}
