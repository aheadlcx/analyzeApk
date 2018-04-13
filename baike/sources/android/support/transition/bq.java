package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(14)
interface bq extends by {
    void add(@NonNull View view);

    void remove(@NonNull View view);
}
