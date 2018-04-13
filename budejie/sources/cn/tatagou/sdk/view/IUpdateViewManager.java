package cn.tatagou.sdk.view;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IUpdateViewManager {
    private static IUpdateViewManager updateViewManager = new IUpdateViewManager();
    private List<UpdateView> updateViews = Collections.synchronizedList(new LinkedList());

    public static synchronized IUpdateViewManager getInstance() {
        IUpdateViewManager iUpdateViewManager;
        synchronized (IUpdateViewManager.class) {
            iUpdateViewManager = updateViewManager;
        }
        return iUpdateViewManager;
    }

    public synchronized void registIUpdateView(UpdateView updateView) {
        this.updateViews.add(0, updateView);
    }

    public synchronized <T> void notifyIUpdateView(String str, T t) {
        List<UpdateView> linkedList = new LinkedList();
        linkedList.addAll(this.updateViews);
        for (UpdateView updateView : linkedList) {
            if (updateView != null && str.equals(updateView.getAction())) {
                updateView.getiUpdateView().updateView(t);
            }
        }
        linkedList.clear();
    }

    public synchronized <T> void notifyIUpdateView(String str, T t, int i) {
        List<UpdateView> linkedList = new LinkedList();
        linkedList.addAll(this.updateViews);
        for (UpdateView updateView : linkedList) {
            if (updateView != null && str.equals(updateView.getAction())) {
                updateView.getiUpdateView().updateView(t, i);
                break;
            }
        }
        linkedList.clear();
    }

    public synchronized void unRegistIUpdateView(String str) {
        this.updateViews.remove(new UpdateView(str, null));
    }

    public synchronized void unRegistIUpdateView(UpdateView updateView) {
        if (updateView != null) {
            this.updateViews.remove(updateView);
        }
    }

    public void unRegistIUpdateView(LinkedList<UpdateView> linkedList) {
        if (linkedList != null && linkedList.size() > 0) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                getInstance().unRegistIUpdateView((UpdateView) it.next());
            }
            linkedList.clear();
        }
    }
}
