package qsbk.app.nearby.api;

public class LocationCache {
    private static LocationCache a;
    private Location b;
    private long c;

    public static class Location {
        public String city;
        public int code;
        public String district;
        public double latitude;
        public double longitude;

        public Location(int i, double d, double d2, String str, String str2) {
            this.code = i;
            this.latitude = d;
            this.longitude = d2;
            this.district = str;
            this.city = str2;
        }

        public String toString() {
            return "Location [code=" + this.code + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", district=" + this.district + ", city=" + this.city + "]";
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals(Object obj) {
            if ((obj instanceof Location) && obj.hashCode() == hashCode()) {
                return true;
            }
            return false;
        }
    }

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
