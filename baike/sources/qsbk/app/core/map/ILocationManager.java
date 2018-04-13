package qsbk.app.core.map;

public interface ILocationManager {
    int getLocation(ILocationCallback iLocationCallback);

    void reinit();

    boolean remove(ILocationCallback iLocationCallback);

    void stop();
}
