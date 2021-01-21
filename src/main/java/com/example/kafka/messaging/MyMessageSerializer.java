package com.example.kafka.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Serialize outbound messages of type MyMessage as array of bytes
 *
 */

@Log
public class MyMessageSerializer implements Serializer<MyMessage>  {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		//Do nothing
	}

	@Override
	public byte[] serialize(String topic, MyMessage data) {
		 byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(data).getBytes();
		    } catch (Exception e) {
				log.severe(e.toString());
		    }
		    return retVal;
	}

	@Override
	public void close() {	
		//Do nothing
	}

}
