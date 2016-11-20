package com.mvmlabs.rest;

import java.util.Date;

import lombok.Data;

@Data
public class Person {
	private String name;
	private String sex;
	private Date dateOfBirth;
}
