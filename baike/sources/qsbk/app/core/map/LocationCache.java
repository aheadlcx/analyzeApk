package qsbk.app.core.map;

public class LocationCache {
    private static LocationCache a;
    private Location b;
    private long c;

    private LocationCache() {
    }

    public static final synchronized LocationCache getInstance() {
        LocationCache locationCache;
        synchronized (LocationCache.class) {
            if (a == null) {
                a = new LocationCache();
            }
            locationCache = a;
        }
        return locationCache;
    }

    public Location getLocation(long j) {
        if (System.currentTimeMillis() - this.c <= j) {
            return this.b;
        }
        return null;
    }

    public void setLocation(Location location) {
        this.b = location;
        this.c = System.currentTimeMillis();
    }
}
