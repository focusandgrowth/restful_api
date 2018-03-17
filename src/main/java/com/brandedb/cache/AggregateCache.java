package com.brandedb.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.joda.time.Instant;

import com.brandedb.datetime.DateTimeUtil;
import com.brandedb.model.SalesOrder;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * The aggregate cache manages inserting and filtering cache records for sales
 * data and top sellers records
 *
 */
public class AggregateCache implements QueryAble {

	private CacheHelper cacheHelper;
	private static AggregateCache aggregateCache;

	public static AggregateCache getInstance() {
		if (aggregateCache == null) {
			aggregateCache = new AggregateCache();
		}
		return aggregateCache;
	}

	private AggregateCache() {
		initCache();
	}

	private void initCache() {
		cacheHelper = new CacheHelper();
	}

	/**
	 * Get back a top seller collection within a date range and limited to the count
	 */
	@Override
	public Collection<SalesOrder> getTopSellingProducts(Instant startDate, Instant endDate,
			int count) {
		List<SalesOrder> topSellers = new ArrayList<SalesOrder>();
		Cache cache = cacheHelper.getSalesOrderCache();
		for (Object key : cache.getKeys()) {
			Element element = cache.get(key);
			SalesOrder cacheEntry = (SalesOrder) element.getObjectValue();

			if (DateTimeUtil.isBetween(startDate, endDate, cacheEntry.getCreatedAt())) {
				topSellers.add(cacheEntry);
			}
		}
		Collections.sort(topSellers);
		int indexCap = topSellers.size() - 1;
		return topSellers.subList(0, (count <= indexCap) ? count : indexCap);
	}

	/**
	 * Get the sales order based on orderId.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Optional<SalesOrder> getSalesOrder(int orderId) {
		Element element = cacheHelper.getSalesOrderCache().get(orderId);
		return (Optional<SalesOrder>) (element == null ? Optional.empty() : Optional.of((SalesOrder)element.getObjectValue()));
	}

	/**
	 * Put sales order in the cache
	 */
	@Override
	public void putSalesOrder(SalesOrder salesOrder) {
		cacheHelper.putSalesOrder(salesOrder);
	}
}
