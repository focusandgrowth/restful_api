package com.brandedb.resources;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brandedb.dao.CacheSalesOrderDAO;
import com.brandedb.dao.SalesOrderDAO;
import com.brandedb.response.Response;
import com.brandedb.service.JsonMapperService;
import com.brandedb.service.JsonMapperServiceImpl;
import com.brandedb.verify.VerifyBuilder;

@Path("putsalesorder")
public class PutSalesOrderResource extends RestResource {

	SalesOrderDAO salesOrderDAO = new CacheSalesOrderDAO();
	JsonMapperService jsonMapperService = new JsonMapperServiceImpl();
	VerifyBuilder verifyBuilder = new VerifyBuilder();

	/**
	 * RESTFul PUT resource to persist sales orders
	 * 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String putSalesOrder(@QueryParam("orderId") int orderId, @QueryParam("product") String product,
			@QueryParam("count") int count, @QueryParam("createdAt") long createdAt,
			@QueryParam("updatedAt") long updatedAt) {
		
		if(!(verifyBuilder
				.objects(product)
				.numbers(orderId, count, createdAt, updatedAt)
				.isVerified())) {
			return jsonMapperService.toJSON(Response.URL_PARAMETERS_INVALID.showResponse());
		}
		int salesOrderAdded = salesOrderDAO.putSalesOrder(orderId, product, count, createdAt, updatedAt);
		return salesOrderAdded == SUCCESS ? 
				  jsonMapperService.toJSON(Response.SALES_ORDER_CREATED.showResponse()) 
				: jsonMapperService.toJSON(Response.SALES_ORDER_COULD_NOT_BE_CREATED.showResponse());
	}
}
