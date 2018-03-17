package com.brandedb.dao;

import java.util.Collection;
import java.util.Optional;

import org.joda.time.Instant;

import com.brandedb.cache.AggregateCache;
import com.brandedb.cache.QueryAble;
import com.brandedb.factory.SalesOrderFactory;
import com.brandedb.model.SalesOrder;

/**
 * Simple DAO used for as an impl that will be responsible for processing or
 * querying SalesOrder data
 *
 */
public class CacheSalesOrderDAO implements SalesOrderDAO {

	QueryAble queryable = AggregateCache.getInstance();

	/**
	 * for the time being the cache will hold the sales order. This can easily be
	 * replaced with a more extensive sales order processing service
	 */
	@Override
	public int putSalesOrder(int orderId, String product, int count, long createdAt, long updatedAt) {
		Optional<SalesOrder> saleProcessed = SalesOrderFactory.buildSalesOrder(orderId, product, count, createdAt,
				updatedAt);
		if (saleProcessed.isPresent()) {
			queryable.putSalesOrder(saleProcessed.get());
			return 1;
		}
		return 0;
	}
	
	@Override
	public void putSalesOrder(SalesOrder salesOrder) {
		queryable.putSalesOrder(salesOrder);
	}
	
	@Override
	public Collection<SalesOrder> getTopSellingProducts(Instant startDate, Instant endDate, int count) {
		return queryable.getTopSellingProducts(startDate, endDate, count);
	}

	/*
	 * Sales orders are stored in the cache and can be easily queried
	 */
	@Override
	public Optional<SalesOrder> getSalesOrder(int orderId) {
		return queryable.getSalesOrder(orderId);
	}
}
