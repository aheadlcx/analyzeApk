package com.ali.auth.third.core.registry.impl;

import com.ali.auth.third.core.registry.ServiceRegistration;
import com.ali.auth.third.core.registry.ServiceRegistry;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DefaultServiceRegistry implements ServiceRegistry {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<ServiceRegistration, ServiceEntry> registrationServiceEntries = new HashMap();
    private Map<Class<?>, List<ServiceEntry>> typeServiceEntries = new HashMap();

    static class InternalServiceRegistration implements ServiceRegistration {
        private Map<String, String> properties;
        private ServiceRegistry serviceRegistry;
        private final String uuid = UUID.randomUUID().toString();

        public InternalServiceRegistration(ServiceRegistry serviceRegistry, Map<String, String> map) {
            this.serviceRegistry = serviceRegistry;
            this.properties = map;
        }

        public void setProperties(Map<String, String> map) {
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    if (!(entry.getKey() == null || entry.getValue() == null)) {
                        this.properties.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        public void unregister() {
            this.serviceRegistry.unregisterService(this);
        }

        public int hashCode() {
            return (this.uuid == null ? 0 : this.uuid.hashCode()) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.uuid.equals(((InternalServiceRegistration) obj).uuid);
        }
    }

    static class ServiceEntry {
        public Object instance;
        public Map<String, String> properties;
        public Class<?>[] types;

        ServiceEntry() {
        }
    }

    public ServiceRegistration registerService(Class<?>[] clsArr, Object obj, Map<String, String> map) {
        if (clsArr == null || clsArr.length == 0 || obj == null) {
            throw new IllegalArgumentException("service types and instance must not be null");
        }
        ServiceEntry serviceEntry = new ServiceEntry();
        serviceEntry.instance = obj;
        serviceEntry.types = clsArr;
        serviceEntry.properties = Collections.synchronizedMap(new HashMap());
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (!(entry.getKey() == null || entry.getValue() == null)) {
                    serviceEntry.properties.put(entry.getKey(), entry.getValue());
                }
            }
        }
        this.lock.writeLock().lock();
        try {
            for (Object obj2 : clsArr) {
                List list = (List) this.typeServiceEntries.get(obj2);
                if (list == null) {
                    list = new ArrayList(2);
                    this.typeServiceEntries.put(obj2, list);
                }
                list.add(serviceEntry);
            }
            ServiceRegistration internalServiceRegistration = new InternalServiceRegistration(this, serviceEntry.properties);
            this.registrationServiceEntries.put(internalServiceRegistration, serviceEntry);
            return internalServiceRegistration;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public <T> T getService(Class<T> cls, Map<String, String> map) {
        this.lock.readLock().lock();
        try {
            List<ServiceEntry> list = (List) this.typeServiceEntries.get(cls);
            if (list == null || list.size() == 0) {
                this.lock.readLock().unlock();
                return null;
            }
            T cast;
            if (map != null) {
                if (map.size() != 0) {
                    for (ServiceEntry serviceEntry : list) {
                        Object obj;
                        for (Entry entry : map.entrySet()) {
                            String str = (String) serviceEntry.properties.get(entry.getKey());
                            if (str != null) {
                                if (!str.equals(entry.getValue())) {
                                }
                            }
                            obj = null;
                            continue;
                        }
                        int i = 1;
                        continue;
                        if (obj != null) {
                            cast = cls.cast(serviceEntry.instance);
                            this.lock.readLock().unlock();
                            return cast;
                        }
                    }
                    this.lock.readLock().unlock();
                    return null;
                }
            }
            cast = cls.cast(((ServiceEntry) list.get(0)).instance);
            this.lock.readLock().unlock();
            return cast;
        } catch (Throwable th) {
            this.lock.readLock().unlock();
        }
    }

    public <T> T[] getServices(Class<T> cls, Map<String, String> map) {
        int i = 0;
        this.lock.readLock().lock();
        try {
            List<ServiceEntry> list = (List) this.typeServiceEntries.get(cls);
            if (list == null || list.size() == 0) {
                T[] tArr = (Object[]) Array.newInstance(cls, 0);
                return tArr;
            }
            if (map != null) {
                if (map.size() != 0) {
                    List arrayList = new ArrayList(list.size());
                    for (ServiceEntry serviceEntry : list) {
                        int i2;
                        for (Entry entry : map.entrySet()) {
                            String str = (String) serviceEntry.properties.get(entry.getKey());
                            if (str != null) {
                                if (!str.equals(entry.getValue())) {
                                }
                            }
                            i2 = 0;
                        }
                        i2 = 1;
                        if (i2 != 0) {
                            arrayList.add(cls.cast(serviceEntry.instance));
                        }
                    }
                    T[] toArray = arrayList.toArray((Object[]) Array.newInstance(cls, arrayList.size()));
                    this.lock.readLock().unlock();
                    return toArray;
                }
            }
            Object[] objArr = (Object[]) Array.newInstance(cls, list.size());
            int size = list.size();
            while (i < size) {
                objArr[i] = cls.cast(((ServiceEntry) list.get(i)).instance);
                i++;
            }
            this.lock.readLock().unlock();
            return objArr;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public Object unregisterService(ServiceRegistration serviceRegistration) {
        Lock lock = null;
        if (serviceRegistration == null) {
            return null;
        }
        this.lock.writeLock().lock();
        try {
            ServiceEntry serviceEntry = (ServiceEntry) this.registrationServiceEntries.remove(serviceRegistration);
            if (serviceEntry == null) {
                return lock;
            }
            if (serviceEntry.types != null) {
                for (Object obj : serviceEntry.types) {
                    List list = (List) this.typeServiceEntries.get(obj);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (it.next() == serviceEntry) {
                            it.remove();
                            break;
                        }
                    }
                    if (list.size() == 0) {
                        this.typeServiceEntries.remove(obj);
                    }
                }
            }
            Object obj2 = serviceEntry.instance;
            this.lock.writeLock().unlock();
            return obj2;
        } finally {
            lock = this.lock.writeLock();
            lock.unlock();
        }
    }
}
