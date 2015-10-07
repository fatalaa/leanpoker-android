package org.leanpoker.data.store;

import com.orhanobut.hawk.Hawk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tmolnar on 07/10/15.
 */
public class MiscStorage {
    private static MiscStorage ourInstance = new MiscStorage();

    public static MiscStorage getInstance() {
        return ourInstance;
    }

    private MiscStorage() {
        mStoredKeys = new HashSet<>();
    }

    private Set<String> mStoredKeys;

    public void put(String key, Object object) {
        mStoredKeys.add(key);
        Hawk.put(key, object);
    }

    public Object get(String key) {
        return Hawk.get(key);
    }

    public void clear() {
        for (String key : mStoredKeys) {
            Hawk.remove(key);
        }
        mStoredKeys.clear();
    }

    public void remove(String key) {
        if (mStoredKeys.contains(key)) {
            Hawk.remove(key);
            mStoredKeys.remove(key);
        }
    }
}
