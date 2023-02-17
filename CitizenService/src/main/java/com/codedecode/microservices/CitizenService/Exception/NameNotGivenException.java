package com.codedecode.microservices.CitizenService.Exception;

import java.io.IOException;

public class NameNotGivenException extends IOException {

	public NameNotGivenException(String message) {
		super(message);
	}
}
