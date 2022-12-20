package com.encypt;

import org.springframework.lang.Nullable;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Customer;

public class EncryptingConverter extends AbstractMessageConverter {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected boolean supports(Class<?> clazz) {
		return clazz.equals(Customer.class);
	}

	@Override
	protected Object convertToInternal(Object payload, @Nullable MessageHeaders headers,
			@Nullable Object conversionHint) {

		String valueAsString = null;
		try {
			valueAsString = mapper.writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		valueAsString = valueAsString.toLowerCase();
		return valueAsString.getBytes();
	}

}
