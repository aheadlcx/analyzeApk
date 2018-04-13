package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import qsbk.app.live.R;

public class GuessResultView extends LinearLayout {
    public GuessResultImageView ivResult1;
    public GuessResultImageView ivResult2;
    public GuessResultImageView ivResult3;
    public GuessResultImageView ivResult4;

    public GuessResultView(Context context) {
        super(context);
        a(null, 0);
    }

    public GuessResultView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public GuessResultView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        setOrientation(0);
        View inflate = View.inflate(getContext(), R.layout.live_guess_result_view, this);
        this.ivResult1 = (GuessResultImageView) inflate.findViewById(R.id.iv_guess_result_1);
        this.ivResult2 = (GuessResultImageView) inflate.findViewById(R.id.iv_guess_result_2);
        this.ivResult3 = (GuessResultImageView) inflate.findViewById(R.id.iv_guess_result_3);
        this.ivResult4 = (GuessResultImageView) inflate.findViewById(R.id.iv_guess_result_4);
    }

    public void setResult(int i, long j) {
        if (j == 1) {
            if (i == 0) {
                this.ivResult1.setStatus(1);
            } else if (i == 1) {
                this.ivResult2.setStatus(1);
            } else if (i == 2) {
                this.ivResult3.setStatus(1);
            } else if (i == 3) {
                this.ivResult4.setStatus(1);
            }
        } else if (j != 2) {
        } else {
            if (i == 0) {
                this.ivResult1.setStatus(2);
            } else if (i == 1) {
                this.ivResult2.setStatus(2);
            } else if (i == 2) {
                this.ivResult3.setStatus(2);
            } else if (i == 3) {
                this.ivResult4.setStatus(2);
            }
        }
    }

    public void setResult(ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int intValue = ((Integer) arrayList.get(i)).intValue();
            if (i == 0) {
                if (intValue == 1) {
                    this.ivResult1.setStatus(1);
                } else if (intValue == 2) {
                    this.ivResult1.setStatus(2);
                }
            } else if (i == 1) {
                if (intValue == 1) {
                    this.ivResult2.setStatus(1);
                } else if (intValue == 2) {
                    this.ivResult2.setStatus(2);
                }
            } else if (i == 2) {
                if (intValue == 1) {
                    this.ivResult3.setStatus(1);
                } else if (intValue == 2) {
                    this.ivResult3.setStatus(2);
                }
            } else if (i == 3) {
                if (intValue == 1) {
                    this.ivResult4.setStatus(1);
                } else if (intValue == 2) {
                    this.ivResult4.setStatus(2);
                }
            }
        }
    }

    public void reset() {
    }

    public void clear() {
        this.ivResult1.setStatus(0);
        this.ivResult2.setStatus(0);
        this.ivResult3.setStatus(0);
        this.ivResult4.setStatus(0);
    }
}
