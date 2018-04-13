package com.facebook.drawee.controller;

class AbstractDraweeController$InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
    private AbstractDraweeController$InternalForwardingListener() {
    }

    public static <INFO> AbstractDraweeController$InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> controllerListener, ControllerListener<? super INFO> controllerListener2) {
        AbstractDraweeController$InternalForwardingListener<INFO> abstractDraweeController$InternalForwardingListener = new AbstractDraweeController$InternalForwardingListener();
        abstractDraweeController$InternalForwardingListener.addListener(controllerListener);
        abstractDraweeController$InternalForwardingListener.addListener(controllerListener2);
        return abstractDraweeController$InternalForwardingListener;
    }
}
