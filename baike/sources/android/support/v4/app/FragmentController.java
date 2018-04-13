package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class FragmentController {
    private final FragmentHostCallback<?> a;

    public static final FragmentController createController(FragmentHostCallback<?> fragmentHostCallback) {
        return new FragmentController(fragmentHostCallback);
    }

    private FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.a = fragmentHostCallback;
    }

    public FragmentManager getSupportFragmentManager() {
        return this.a.d();
    }

    public LoaderManager getSupportLoaderManager() {
        return this.a.e();
    }

    @Nullable
    public Fragment findFragmentByWho(String str) {
        return this.a.d.findFragmentByWho(str);
    }

    public int getActiveFragmentsCount() {
        return this.a.d.b();
    }

    public List<Fragment> getActiveFragments(List<Fragment> list) {
        return this.a.d.a();
    }

    public void attachHost(Fragment fragment) {
        this.a.d.attachController(this.a, this.a, fragment);
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.a.d.onCreateView(view, str, context, attributeSet);
    }

    public void noteStateNotSaved() {
        this.a.d.noteStateNotSaved();
    }

    public Parcelable saveAllState() {
        return this.a.d.h();
    }

    @Deprecated
    public void restoreAllState(Parcelable parcelable, List<Fragment> list) {
        this.a.d.a(parcelable, new FragmentManagerNonConfig(list, null));
    }

    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.a.d.a(parcelable, fragmentManagerNonConfig);
    }

    @Deprecated
    public List<Fragment> retainNonConfig() {
        FragmentManagerNonConfig f = this.a.d.f();
        return f != null ? f.a() : null;
    }

    public FragmentManagerNonConfig retainNestedNonConfig() {
        return this.a.d.f();
    }

    public void dispatchCreate() {
        this.a.d.dispatchCreate();
    }

    public void dispatchActivityCreated() {
        this.a.d.dispatchActivityCreated();
    }

    public void dispatchStart() {
        this.a.d.dispatchStart();
    }

    public void dispatchResume() {
        this.a.d.dispatchResume();
    }

    public void dispatchPause() {
        this.a.d.dispatchPause();
    }

    public void dispatchStop() {
        this.a.d.dispatchStop();
    }

    public void dispatchReallyStop() {
        this.a.d.dispatchReallyStop();
    }

    public void dispatchDestroyView() {
        this.a.d.dispatchDestroyView();
    }

    public void dispatchDestroy() {
        this.a.d.dispatchDestroy();
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        this.a.d.dispatchMultiWindowModeChanged(z);
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        this.a.d.dispatchPictureInPictureModeChanged(z);
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        this.a.d.dispatchConfigurationChanged(configuration);
    }

    public void dispatchLowMemory() {
        this.a.d.dispatchLowMemory();
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        return this.a.d.dispatchCreateOptionsMenu(menu, menuInflater);
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        return this.a.d.dispatchPrepareOptionsMenu(menu);
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        return this.a.d.dispatchOptionsItemSelected(menuItem);
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        return this.a.d.dispatchContextItemSelected(menuItem);
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        this.a.d.dispatchOptionsMenuClosed(menu);
    }

    public boolean execPendingActions() {
        return this.a.d.execPendingActions();
    }

    public void doLoaderStart() {
        this.a.g();
    }

    public void doLoaderStop(boolean z) {
        this.a.a(z);
    }

    public void doLoaderRetain() {
        this.a.h();
    }

    public void doLoaderDestroy() {
        this.a.i();
    }

    public void reportLoaderStart() {
        this.a.j();
    }

    public SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
        return this.a.k();
    }

    public void restoreLoaderNonConfig(SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
        this.a.a((SimpleArrayMap) simpleArrayMap);
    }

    public void dumpLoaders(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.a.a(str, fileDescriptor, printWriter, strArr);
    }
}
