package com.brandedb.resources;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brandedb.dao.CacheSalesOrderDAO;
import com.brandedb.dao.SalesOrderDAO;
import com.brandedb.model.SalesOrder;
import com.brandedb.response.Response;
import com.brandedb.service.JsonMapperService;
import com.brandedb.service.JsonMapperServiceImpl;
import com.brandedb.verify.VerifyBuilder;

@Path("getsalesorder")
public class GetSalesOrderResource extends RestResource {

	SalesOrderDAO salesOrderDAO = new CacheSalesOrderDAO();
	JsonMapperService jsonMapperService = new JsonMapperServiceImpl();
	VerifyBuilder verifyBuilder = new VerifyBuilder();

	/**
	 * RESTFul GET resource to fetch sales order
	 * 
	 * @param orderId
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSalesOrder(@QueryParam("orderId") int orderId) {
		if (!(verifyBuilder
				.numbers(orderId)
				.isVerified())) {
			return jsonMapperService.toJSON(Response.URL_PARAMETERS_INVALID.showResponse());
		}
		Optional<SalesOrder> salesOrder = salesOrderDAO.getSalesOrder(orderId);
		return jsonMapperService.toJSON(salesOrder.isPresent() ? salesOrder.get()
				: Response.SALES_ORDER_COULD_NOT_BE_RETRIEVED.showResponse());
	}

}
