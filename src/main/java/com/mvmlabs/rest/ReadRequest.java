package com.mvmlabs.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ReadRequest {
	
	@NotNull
	@Pattern(regexp="^[A-Za-z]*[A-Za-z-'. ]*[A-Za-z]*$")
	private String name;
}
