package com.brandedb.model;

import java.io.Serializable;

import org.joda.time.Instant;

/**
 * SalesOrder is a holder to encapsulate sales order data received from the
 * restful service. It can be serialized to disk and cachable in the Ehcache
 *
 */
public class SalesOrder implements Serializable, Comparable<SalesOrder> {

	private static final long serialVersionUID = 771238512724726679L;
	private final int orderId;
	private final String product;
	private final int count;
	private final Instant createdAt;
	private final Instant updatedAt;

	public SalesOrder(int orderId, String product, int count, Instant createdAt, Instant updatedAt) {
		this.orderId = orderId;
		this.product = product;
		this.count = count;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getProduct() {
		return product;
	}

	public int getCount() {
		return count;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	@Override
	public int compareTo(SalesOrder other) {
		return (other.count - this.count);
	}

	@Override
	public String toString() {
		return "SalesOrder: updatedAt:" + updatedAt + " :createdAt:" + createdAt + ":count:" + count + ":product:"
				+ product + ":orderid:" + orderId;
	}
}
