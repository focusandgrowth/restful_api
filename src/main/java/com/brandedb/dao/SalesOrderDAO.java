package com.brandedb.dao;

import java.util.Collection;
import java.util.Optional;

import org.joda.time.Instant;

import com.brandedb.model.SalesOrder;

/**
 * Interface used for any impl that will be responsible for processing or
 * querying SalesOrder data
 *
 */
public interface SalesOrderDAO {

	public int putSalesOrder(int orderId, String product, int count, long createdAt, long updatedAt);
	
	public void putSalesOrder(SalesOrder salesOrder);

	public Optional<SalesOrder> getSalesOrder(int orderId);
	
	public Collection<SalesOrder> getTopSellingProducts(Instant startDate, Instant endDate, int count);
}
