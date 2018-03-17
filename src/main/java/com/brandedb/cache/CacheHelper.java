package com.brandedb.cache;

import java.util.Collection;

import com.brandedb.file.SalesOrderReader;
import com.brandedb.model.SalesOrder;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * Manages the references and creation of the cache to manage top seller and
 * sales order data
 *
 */
public class CacheHelper {

	private CacheManager cacheManager;
	private static final String SALES_ORDERS_CACHE_KEY = "salesOrders";
	private Cache salesOrderCache;

	public CacheHelper() {
		cacheManager = CacheManager.getInstance();
		initCache();
		populateSalesOrderSeedData();
	}

	private void initCache() {
		salesOrderCache = new Cache(new CacheConfiguration(SALES_ORDERS_CACHE_KEY, 100)
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU).eternal(false)
				.persistence(new PersistenceConfiguration().strategy(net.sf.ehcache.config.PersistenceConfiguration.Strategy.LOCALTEMPSWAP)));
		cacheManager.addCache(salesOrderCache);
	}
	
	private void populateSalesOrderSeedData() {
		Collection<SalesOrder> salesOrders = new SalesOrderReader().getSalesOrdersFromCSV();
		for(SalesOrder salesOrder: salesOrders) {
			putSalesOrder(salesOrder);
		}
	}

	public Cache getSalesOrderCache() {
		return salesOrderCache;
	}

	/**
	 * put sales order in cache
	 * 
	 * @param salesOrder
	 */
	public void putSalesOrder(SalesOrder salesOrder) {
		salesOrderCache.put(new Element(salesOrder.getOrderId(), salesOrder));
	}
}
