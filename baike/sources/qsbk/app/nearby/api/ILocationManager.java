package qsbk.app.nearby.api;

public interface ILocationManager {
    int getLocation(ILocationCallback iLocationCallback);

    void reinit();

    boolean remove(ILocationCallback iLocationCallback);

    void stop();
}
