package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private StreamBridge streamBridge;

	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
	public Customer addCustomer(@PathVariable("id") int id) throws JsonProcessingException {
		// add default name
		Customer defaultCustomer = new Customer(id, "Dwayne", "NY");
		Message<Customer> message = MessageBuilder.withPayload(defaultCustomer).setHeader("Header1", "value1")
				.setHeader("Header2", "value2").build();

		//String valueAsString = mapper.writeValueAsString(defaultCustomer);
		// Message<byte[]> message =
		// MessageBuilder.withPayload(valueAsString.getBytes()).setHeader("Header1",
		// "value1").setHeader("Header2", "value2").build();
		// streamBridge.send("customerBinding-out-0", valueAsString.getBytes());

		streamBridge.send("customerBinding-out-0", message);
		System.out.println("Sending Message:" + message);
		return defaultCustomer;
	}

}
