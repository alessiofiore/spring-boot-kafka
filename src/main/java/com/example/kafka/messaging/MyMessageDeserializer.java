package com.example.kafka.messaging;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

/**
 * Deserialize inbound array of bytes messages as MyMessage
 *
 */

@Log
public class MyMessageDeserializer implements Deserializer<MyMessage> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		//Do nothing
	}

	@Override
	public MyMessage deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		MyMessage message = null;
		try {
			message = mapper.readValue(data, MyMessage.class);
		} catch (Exception e) {
			log.severe(e.toString());
		}
		return message;
	}

	@Override
	public void close() {
		//Do nothing
	}

}
