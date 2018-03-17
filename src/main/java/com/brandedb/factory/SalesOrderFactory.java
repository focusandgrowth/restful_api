package com.brandedb.factory;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.Instant;

import com.brandedb.datetime.DateTimeUtil;
import com.brandedb.model.SalesOrder;
import com.brandedb.verify.VerifyBuilder;

/**
 * Simple factory used to create/verify values are correct to create SalesOrder
 * models
 *
 */
public class SalesOrderFactory {

	/**
	 * Returns Optional to avoid NPException down the calling path
	 * 
	 */
	public static Optional<SalesOrder> buildSalesOrder(int orderId, String product, int count, long createdAt,
			long updatedAt) {
		if (!(new VerifyBuilder()
				.objects(product)
				.numbers(orderId, count, createdAt, updatedAt)
				.isVerified())) {
			Logger.getLogger("com.brandedb").log(Level.SEVERE, "Sales order properties must be valid");
			return Optional.empty();
		}
		return buildSalesOrder(orderId, product, count, DateTimeUtil.toInstant(createdAt), DateTimeUtil.toInstant(updatedAt));
		
	}
	
	/**
	 * Returns Optional to avoid NPException down the calling path
	 * 
	 */
	public static Optional<SalesOrder> buildSalesOrder(int orderId, String product, int count, Instant createdAt,
			Instant updatedAt){
		if (!(new VerifyBuilder()
				.objects(product, createdAt, updatedAt)
				.numbers(orderId, count)
				.isVerified())) {
			Logger.getLogger("com.brandedb").log(Level.SEVERE, "Sales order properties must be valid");
			return Optional.empty();
		}
		SalesOrder salesOrder = new SalesOrder(orderId, product, count, createdAt, updatedAt);
		return Optional.of(salesOrder);
	}
}