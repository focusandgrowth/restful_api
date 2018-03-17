package com.brandedb.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.brandedb.general.ObjectUtil;

public class VerifyBuilder {

	Collection<Object> objects = new ArrayList<Object>();
	Collection<Number> numbers = new ArrayList<Number>();

	public VerifyBuilder objects(Object... os) {
		objects = Arrays.asList(os);
		return this;
	}

	public VerifyBuilder numbers(Number... ns) {
		numbers = Arrays.asList(ns);
		return this;
	}

	public boolean isVerified() {
		boolean valid = true;
		for (Object o : objects) {
			if (ObjectUtil.isNull(o)) {
				valid = false;
			}
		}
		for (Number n : numbers) {
			if (n.intValue() <= 0) {
				valid = false;
			}
		}
		return valid;
	}
}
