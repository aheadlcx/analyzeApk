package com.google.android.flexbox;

import android.view.View;
import java.util.List;

interface a {
    int a(int i, int i2, int i3);

    int a(View view);

    int a(View view, int i, int i2);

    View a(int i);

    void a(int i, View view);

    void a(View view, int i, int i2, c cVar);

    void a(c cVar);

    boolean a();

    int b(int i, int i2, int i3);

    View b(int i);

    int getAlignContent();

    int getAlignItems();

    int getFlexDirection();

    int getFlexItemCount();

    List<c> getFlexLinesInternal();

    int getFlexWrap();

    int getLargestMainSize();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    int getSumOfCrossSize();

    void setFlexLines(List<c> list);
}
