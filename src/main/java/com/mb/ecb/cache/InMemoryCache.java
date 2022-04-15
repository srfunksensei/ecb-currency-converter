package com.mb.ecb.cache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache<K, V> {

    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

    public Optional<V> getValue(final K key) {
        final V value = cache.getOrDefault(key, null);
        return Optional.ofNullable(value);
    }

    public void cacheValue(final K key, final V value) {
        cache.put(key, value);
    }

    public boolean containsKey(final K key) {
        return cache.containsKey(key);
    }
}
