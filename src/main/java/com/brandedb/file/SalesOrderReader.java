package com.brandedb.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.brandedb.datetime.DateTimeUtil;
import com.brandedb.factory.SalesOrderFactory;
import com.brandedb.model.SalesOrder;

public class SalesOrderReader {

	public Collection<SalesOrder> getSalesOrdersFromCSV() {
		
		Collection<SalesOrder> salesOrders = new ArrayList<>();
		CSVReader csvReader = new CSVReader();
		for (String[] lineItem : csvReader.readFile()) {
			Optional<SalesOrder> salesOrder = SalesOrderFactory.buildSalesOrder(
					Integer.parseInt(lineItem[0]), 
					lineItem[1],
					Integer.parseInt(lineItem[2]), 
					DateTimeUtil.toInstant(lineItem[3]), 
					DateTimeUtil.toInstant(lineItem[4]));
			if (salesOrder.isPresent()) {
				salesOrders.add(salesOrder.get());
			}
		}
		return salesOrders;
	}
}

class CSVReader {

	Collection<String[]> readFile() {
		
		Collection<String[]> lineItems = new ArrayList<>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(getClass().getResource("orders.csv").getFile()));
			while ((line = br.readLine()) != null) {
				String[] lineItem = line.split(cvsSplitBy);
				lineItems.add(lineItem);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lineItems;
	}
}
