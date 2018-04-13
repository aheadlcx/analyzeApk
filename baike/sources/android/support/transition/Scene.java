package android.support.transition;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene {
    private Context a;
    private int b = -1;
    private ViewGroup c;
    private View d;
    private Runnable e;
    private Runnable f;

    @NonNull
    public static Scene getSceneForLayout(@NonNull ViewGroup viewGroup, @LayoutRes int i, @NonNull Context context) {
        SparseArray sparseArray;
        SparseArray sparseArray2 = (SparseArray) viewGroup.getTag(R.id.transition_scene_layoutid_cache);
        if (sparseArray2 == null) {
            sparseArray2 = new SparseArray();
            viewGroup.setTag(R.id.transition_scene_layoutid_cache, sparseArray2);
            sparseArray = sparseArray2;
        } else {
            sparseArray = sparseArray2;
        }
        Scene scene = (Scene) sparseArray.get(i);
        if (scene != null) {
            return scene;
        }
        scene = new Scene(viewGroup, i, context);
        sparseArray.put(i, scene);
        return scene;
    }

    public Scene(@NonNull ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    private Scene(ViewGroup viewGroup, int i, Context context) {
        this.a = context;
        this.c = viewGroup;
        this.b = i;
    }

    public Scene(@NonNull ViewGroup viewGroup, @NonNull View view) {
        this.c = viewGroup;
        this.d = view;
    }

    @NonNull
    public ViewGroup getSceneRoot() {
        return this.c;
    }

    public void exit() {
        if (a(this.c) == this && this.f != null) {
            this.f.run();
        }
    }

    public void enter() {
        if (this.b > 0 || this.d != null) {
            getSceneRoot().removeAllViews();
            if (this.b > 0) {
                LayoutInflater.from(this.a).inflate(this.b, this.c);
            } else {
                this.c.addView(this.d);
            }
        }
        if (this.e != null) {
            this.e.run();
        }
        a(this.c, this);
    }

    static void a(View view, Scene scene) {
        view.setTag(R.id.transition_current_scene, scene);
    }

    static Scene a(View view) {
        return (Scene) view.getTag(R.id.transition_current_scene);
    }

    public void setEnterAction(@Nullable Runnable runnable) {
        this.e = runnable;
    }

    public void setExitAction(@Nullable Runnable runnable) {
        this.f = runnable;
    }

    boolean a() {
        return this.b > 0;
    }
}
