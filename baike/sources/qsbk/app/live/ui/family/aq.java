package qsbk.app.live.ui.family;

class aq implements Runnable {
    final /* synthetic */ FamilyMemberActivity a;

    aq(FamilyMemberActivity familyMemberActivity) {
        this.a = familyMemberActivity;
    }

    public void run() {
        this.a.e.setRefreshing(true);
        this.a.e();
    }
}
