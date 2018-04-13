package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class q implements Continuation<Void, List<TResult>> {
    final /* synthetic */ Collection a;

    q(Collection collection) {
        this.a = collection;
    }

    public List<TResult> then(Task<Void> task) throws Exception {
        if (this.a.size() == 0) {
            return Collections.emptyList();
        }
        List<TResult> arrayList = new ArrayList();
        for (Task result : this.a) {
            arrayList.add(result.getResult());
        }
        return arrayList;
    }
}
