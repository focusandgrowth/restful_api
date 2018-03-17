package com.brandedb.response;

public enum Response {

	SUCCESS("Success"), 
	FAILURE("Failure"), 
	URL_PARAMETERS_INVALID("URL parameters invalid"), 
	SALES_ORDER_COULD_NOT_BE_RETRIEVED("Sales order could not be retrieved"), 
	SALES_ORDER_CREATED("Sales order created"), 
	SALES_ORDER_COULD_NOT_BE_CREATED("Sales order could not be created");

	private String response;

	Response(String response) {
		this.response = response;
	}

	public String showResponse() {
		return response;
	}

}
