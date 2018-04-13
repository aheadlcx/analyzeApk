package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionManager {
    private static Transition a = new AutoTransition();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> d = new ThreadLocal();
    private static ArrayList<ViewGroup> e = new ArrayList();
    private ArrayMap<Scene, Transition> b = new ArrayMap();
    private ArrayMap<Scene, ArrayMap<Scene, Transition>> c = new ArrayMap();

    private static class a implements OnAttachStateChangeListener, OnPreDrawListener {
        Transition a;
        ViewGroup b;

        a(Transition transition, ViewGroup viewGroup) {
            this.a = transition;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            a();
            TransitionManager.e.remove(this.b);
            ArrayList arrayList = (ArrayList) TransitionManager.a().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.b);
                }
            }
            this.a.a(true);
        }

        public boolean onPreDraw() {
            a();
            if (TransitionManager.e.remove(this.b)) {
                ArrayList arrayList;
                ArrayMap a = TransitionManager.a();
                ArrayList arrayList2 = (ArrayList) a.get(this.b);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    a.put(this.b, arrayList2);
                    arrayList = arrayList2;
                    arrayList2 = null;
                } else if (arrayList2.size() > 0) {
                    ArrayList arrayList3 = new ArrayList(arrayList2);
                    arrayList = arrayList2;
                    arrayList2 = arrayList3;
                } else {
                    arrayList = arrayList2;
                    arrayList2 = null;
                }
                arrayList.add(this.a);
                this.a.addListener(new bi(this, a));
                this.a.a(this.b, false);
                if (arrayList2 != null) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((Transition) it.next()).resume(this.b);
                    }
                }
                this.a.a(this.b);
            }
            return true;
        }
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        this.b.put(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        ArrayMap arrayMap = (ArrayMap) this.c.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap();
            this.c.put(scene2, arrayMap);
        }
        arrayMap.put(scene, transition);
    }

    private Transition a(Scene scene) {
        Transition transition;
        View sceneRoot = scene.getSceneRoot();
        if (sceneRoot != null) {
            Scene a = Scene.a(sceneRoot);
            if (a != null) {
                ArrayMap arrayMap = (ArrayMap) this.c.get(scene);
                if (arrayMap != null) {
                    transition = (Transition) arrayMap.get(a);
                    if (transition != null) {
                        return transition;
                    }
                }
            }
        }
        transition = (Transition) this.b.get(scene);
        return transition == null ? a : transition;
    }

    private static void a(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (!e.contains(sceneRoot)) {
            if (transition == null) {
                scene.enter();
                return;
            }
            e.add(sceneRoot);
            Transition clone = transition.clone();
            clone.c(sceneRoot);
            Scene a = Scene.a(sceneRoot);
            if (a != null && a.a()) {
                clone.b(true);
            }
            b(sceneRoot, clone);
            scene.enter();
            a(sceneRoot, clone);
        }
    }

    static ArrayMap<ViewGroup, ArrayList<Transition>> a() {
        WeakReference weakReference = (WeakReference) d.get();
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference(new ArrayMap());
            d.set(weakReference);
        }
        return (ArrayMap) weakReference.get();
    }

    private static void a(ViewGroup viewGroup, Transition transition) {
        if (transition != null && viewGroup != null) {
            Object aVar = new a(transition, viewGroup);
            viewGroup.addOnAttachStateChangeListener(aVar);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
        }
    }

    private static void b(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) a().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.a(viewGroup, true);
        }
        Scene a = Scene.a(viewGroup);
        if (a != null) {
            a.exit();
        }
    }

    public void transitionTo(@NonNull Scene scene) {
        a(scene, a(scene));
    }

    public static void go(@NonNull Scene scene) {
        a(scene, a);
    }

    public static void go(@NonNull Scene scene, @Nullable Transition transition) {
        a(scene, transition);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        if (!e.contains(viewGroup) && ViewCompat.isLaidOut(viewGroup)) {
            e.add(viewGroup);
            if (transition == null) {
                transition = a;
            }
            Transition clone = transition.clone();
            b(viewGroup, clone);
            Scene.a(viewGroup, null);
            a(viewGroup, clone);
        }
    }

    public static void endTransitions(ViewGroup viewGroup) {
        e.remove(viewGroup);
        ArrayList arrayList = (ArrayList) a().get(viewGroup);
        if (arrayList != null && !arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(arrayList);
            for (int size = arrayList2.size() - 1; size >= 0; size--) {
                ((Transition) arrayList2.get(size)).b(viewGroup);
            }
        }
    }
}
