package com.mvmlabs.rest;

import lombok.Data;

@Data
public class ReadResponse {
	private boolean success;
	private Person person;
}
