package android.support.transition;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import qsbk.app.database.QsbkDatabase;

public class TransitionInflater {
    private static final Class<?>[] a = new Class[]{Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor> b = new ArrayMap();
    private final Context c;

    private TransitionInflater(@NonNull Context context) {
        this.c = context;
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    public Transition inflateTransition(int i) {
        XmlPullParser xml = this.c.getResources().getXml(i);
        try {
            Transition a = a(xml, Xml.asAttributeSet(xml), null);
            xml.close();
            return a;
        } catch (Throwable e) {
            throw new InflateException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new InflateException(xml.getPositionDescription() + ": " + e2.getMessage(), e2);
        } catch (Throwable th) {
            xml.close();
        }
    }

    public TransitionManager inflateTransitionManager(int i, ViewGroup viewGroup) {
        InflateException inflateException;
        XmlPullParser xml = this.c.getResources().getXml(i);
        try {
            TransitionManager a = a(xml, Xml.asAttributeSet(xml), viewGroup);
            xml.close();
            return a;
        } catch (Throwable e) {
            inflateException = new InflateException(e.getMessage());
            inflateException.initCause(e);
            throw inflateException;
        } catch (Throwable e2) {
            inflateException = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Throwable th) {
            xml.close();
        }
    }

    private Transition a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        TransitionSet transitionSet = transition instanceof TransitionSet ? (TransitionSet) transition : null;
        Transition transition2 = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    Transition fade;
                    String name = xmlPullParser.getName();
                    if ("fade".equals(name)) {
                        fade = new Fade(this.c, attributeSet);
                    } else if ("changeBounds".equals(name)) {
                        fade = new ChangeBounds(this.c, attributeSet);
                    } else if ("slide".equals(name)) {
                        fade = new Slide(this.c, attributeSet);
                    } else if ("explode".equals(name)) {
                        fade = new Explode(this.c, attributeSet);
                    } else if ("changeImageTransform".equals(name)) {
                        fade = new ChangeImageTransform(this.c, attributeSet);
                    } else if ("changeTransform".equals(name)) {
                        fade = new ChangeTransform(this.c, attributeSet);
                    } else if ("changeClipBounds".equals(name)) {
                        fade = new ChangeClipBounds(this.c, attributeSet);
                    } else if ("autoTransition".equals(name)) {
                        fade = new AutoTransition(this.c, attributeSet);
                    } else if ("changeScroll".equals(name)) {
                        fade = new ChangeScroll(this.c, attributeSet);
                    } else if ("transitionSet".equals(name)) {
                        fade = new TransitionSet(this.c, attributeSet);
                    } else if ("transition".equals(name)) {
                        fade = (Transition) a(attributeSet, Transition.class, "transition");
                    } else if ("targets".equals(name)) {
                        b(xmlPullParser, attributeSet, transition);
                        fade = transition2;
                    } else if ("arcMotion".equals(name)) {
                        if (transition == null) {
                            throw new RuntimeException("Invalid use of arcMotion element");
                        }
                        transition.setPathMotion(new ArcMotion(this.c, attributeSet));
                        fade = transition2;
                    } else if ("pathMotion".equals(name)) {
                        if (transition == null) {
                            throw new RuntimeException("Invalid use of pathMotion element");
                        }
                        transition.setPathMotion((PathMotion) a(attributeSet, PathMotion.class, "pathMotion"));
                        fade = transition2;
                    } else if (!"patternPathMotion".equals(name)) {
                        throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                    } else if (transition == null) {
                        throw new RuntimeException("Invalid use of patternPathMotion element");
                    } else {
                        transition.setPathMotion(new PatternPathMotion(this.c, attributeSet));
                        fade = transition2;
                    }
                    if (fade != null) {
                        if (!xmlPullParser.isEmptyElementTag()) {
                            a(xmlPullParser, attributeSet, fade);
                        }
                        if (transitionSet != null) {
                            transitionSet.addTransition(fade);
                            fade = null;
                        } else if (transition != null) {
                            throw new InflateException("Could not add transition to another transition.");
                        }
                    }
                    transition2 = fade;
                }
            }
        }
        return transition2;
    }

    private Object a(AttributeSet attributeSet, Class cls, String str) {
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        if (attributeValue == null) {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
        try {
            Object newInstance;
            synchronized (b) {
                Constructor constructor = (Constructor) b.get(attributeValue);
                if (constructor == null) {
                    Class asSubclass = this.c.getClassLoader().loadClass(attributeValue).asSubclass(cls);
                    if (asSubclass != null) {
                        constructor = asSubclass.getConstructor(a);
                        constructor.setAccessible(true);
                        b.put(attributeValue, constructor);
                    }
                }
                newInstance = constructor.newInstance(new Object[]{this.c, attributeSet});
            }
            return newInstance;
        } catch (Throwable e) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e);
        }
    }

    private void b(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                if (xmlPullParser.getName().equals(QsbkDatabase.TARGET)) {
                    TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(attributeSet, be.a);
                    next = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "targetId", 1, 0);
                    if (next != 0) {
                        transition.addTarget(next);
                    } else {
                        next = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "excludeId", 2, 0);
                        if (next != 0) {
                            transition.excludeTarget(next, true);
                        } else {
                            String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetName", 4);
                            if (namedString != null) {
                                transition.addTarget(namedString);
                            } else {
                                namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeName", 5);
                                if (namedString != null) {
                                    transition.excludeTarget(namedString, true);
                                } else {
                                    namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeClass", 3);
                                    if (namedString != null) {
                                        try {
                                            transition.excludeTarget(Class.forName(namedString), true);
                                        } catch (Throwable e) {
                                            obtainStyledAttributes.recycle();
                                            throw new RuntimeException("Could not create " + namedString, e);
                                        }
                                    }
                                    namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetClass", 0);
                                    if (namedString != null) {
                                        transition.addTarget(Class.forName(namedString));
                                    }
                                }
                            }
                        }
                    }
                    obtainStyledAttributes.recycle();
                } else {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.transition.TransitionManager a(org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6, android.view.ViewGroup r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r4 = this;
        r1 = r5.getDepth();
        r0 = 0;
    L_0x0005:
        r2 = r5.next();
        r3 = 3;
        if (r2 != r3) goto L_0x0012;
    L_0x000c:
        r3 = r5.getDepth();
        if (r3 <= r1) goto L_0x0055;
    L_0x0012:
        r3 = 1;
        if (r2 == r3) goto L_0x0055;
    L_0x0015:
        r3 = 2;
        if (r2 != r3) goto L_0x0005;
    L_0x0018:
        r2 = r5.getName();
        r3 = "transitionManager";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x002a;
    L_0x0024:
        r0 = new android.support.transition.TransitionManager;
        r0.<init>();
        goto L_0x0005;
    L_0x002a:
        r3 = "transition";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0038;
    L_0x0032:
        if (r0 == 0) goto L_0x0038;
    L_0x0034:
        r4.a(r6, r5, r7, r0);
        goto L_0x0005;
    L_0x0038:
        r0 = new java.lang.RuntimeException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unknown scene name: ";
        r1 = r1.append(r2);
        r2 = r5.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0055:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionInflater.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):android.support.transition.TransitionManager");
    }

    private void a(AttributeSet attributeSet, XmlPullParser xmlPullParser, ViewGroup viewGroup, TransitionManager transitionManager) throws NotFoundException {
        Scene scene = null;
        TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(attributeSet, be.b);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "transition", 2, -1);
        int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "fromScene", 0, -1);
        Scene sceneForLayout = namedResourceId2 < 0 ? null : Scene.getSceneForLayout(viewGroup, namedResourceId2, this.c);
        int namedResourceId3 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "toScene", 1, -1);
        if (namedResourceId3 >= 0) {
            scene = Scene.getSceneForLayout(viewGroup, namedResourceId3, this.c);
        }
        if (namedResourceId >= 0) {
            Transition inflateTransition = inflateTransition(namedResourceId);
            if (inflateTransition != null) {
                if (scene == null) {
                    throw new RuntimeException("No toScene for transition ID " + namedResourceId);
                } else if (sceneForLayout == null) {
                    transitionManager.setTransition(scene, inflateTransition);
                } else {
                    transitionManager.setTransition(sceneForLayout, scene, inflateTransition);
                }
            }
        }
        obtainStyledAttributes.recycle();
    }
}
