package com.mvmlabs.rest;

import lombok.Data;

@Data
public class StoreResponse {
	private boolean success;
	private boolean updated;
	private String errorMessage;
}
