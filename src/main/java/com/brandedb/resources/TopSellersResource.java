package com.brandedb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.brandedb.dao.CacheSalesOrderDAO;
import com.brandedb.dao.SalesOrderDAO;
import com.brandedb.datetime.DateTimeUtil;
import com.brandedb.response.Response;
import com.brandedb.service.JsonMapperService;
import com.brandedb.service.JsonMapperServiceImpl;
import com.brandedb.verify.VerifyBuilder;

/**
 * Root resource (exposed at "top sellers" path)
 */
@Path("topsellers")
public class TopSellersResource extends RestResource {

	JsonMapperService jsonMapperService = new JsonMapperServiceImpl();
	SalesOrderDAO salesOrderDAO = new CacheSalesOrderDAO();
	VerifyBuilder verifyBuilder = new VerifyBuilder();

	/**
	 * RESTFul GET resource to query top sellers
	 * 
	 * @param startDate
	 * @param endDate
	 * @param count
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTopSelleringProducts(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("count") int count) {
		if(!(verifyBuilder
				.objects(startDate, endDate)
				.numbers(count)
				.isVerified())) {
			return jsonMapperService.toJSON(Response.URL_PARAMETERS_INVALID.showResponse());
		}
		return jsonMapperService.toJSON(salesOrderDAO.getTopSellingProducts(DateTimeUtil.toInstant(startDate),
				DateTimeUtil.toInstant(endDate), count));
	}
}
