package at.easydiet.util;

import java.util.Collection;

/**
 * Caches i.e. a sorted collection
 * 
 * @param <T>
 *            Type of the collection elements
 */
public abstract class CollectionCache<T>
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(CollectionCache.class);

    /**
     * Gets the original collection
     * 
     * @return Original collection
     */
    protected abstract Collection<T> getOriginal();

    /**
     * Refreshes the cache
     */
    protected abstract void refreshCache();

    /**
     * Hash code
     */
    private int         _cacheState = -1;
    /**
     * Stores the cached collection
     */
    private Collection<T> _cache;

    /**
     * Sets the cached collection
     * 
     * @param newCache
     *            The new cached collection
     */
    protected void setCache(Collection<T> newCache)
    {
        _cache = newCache;
        _cacheState = getOriginal().hashCode();
    }

    /**
     * Gets the cached collection
     * 
     * @return The cached collection
     */
    public Collection<T> getCache()
    {
        if (_cacheState != getOriginal().hashCode())
        {
            refreshCache();
        }
        return _cache;
    }
}
