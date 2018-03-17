package com.brandedb.cache;

import java.util.Collection;
import java.util.Optional;

import org.joda.time.Instant;

import com.brandedb.model.SalesOrder;

/**
 * Interface used for the aggregate cache and created to be interacting with as
 * injection is put in place
 *
 */
public interface QueryAble {

	public Collection<SalesOrder> getTopSellingProducts(Instant startDate, Instant  endDate, int count);

	public Optional<SalesOrder> getSalesOrder(int orderId);

	public void putSalesOrder(SalesOrder salesOrder);
}
