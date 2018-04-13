package com.budejie.www.activity.label.enumeration;

import android.content.Context;
import com.budejie.www.bean.ListItemObject;

public class CommonLabelOperator {
    public Context context;
    public ListItemObject data;
    public CommonLabelAction operatorAction;

    public enum CommonLabelAction {
        ADD_ESSENCE,
        TO_TOP,
        DELETE_POST
    }
}
