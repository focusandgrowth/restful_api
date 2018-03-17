package com.brandedb.verify;

import java.util.ArrayList;
import java.util.Collection;

import com.brandedb.general.ObjectUtil;

public class VerifyBuilder {

	Collection<Object> objects = new ArrayList<Object>();
	Collection<Number> numbers = new ArrayList<Number>();

	public VerifyBuilder objects(Object... os) {
		for(Object o:os) {
			objects.add(o);
		}
		return this;
	}

	public VerifyBuilder numbers(Number... ns) {
		for(Number n:ns){
			numbers.add(n);
		}
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
