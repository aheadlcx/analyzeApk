package cn.v6.sixrooms.ui.fragment;

final class a implements Runnable {
    final /* synthetic */ BundlePhoneFragment a;

    a(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void run() {
        BundlePhoneFragment.a(this.a).toggleSoftInput(0, 2);
    }
}
