package com.mvmlabs.rest;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * A transport object that is validated and checks inbound data before it is copied into an object that will be used for data storgae etc. to avoid sql injection etc.
 *
 * See: http://docs.oracle.com/javaee/6/tutorial/doc/gircz.html
 * 
 * @author mark
 */

@Data
public class StoreRequest {

	@NotNull
	@Pattern(regexp="^[A-Za-z]*[A-Za-z-'. ]*[A-Za-z]*$")
	@Size(min=3, max=30)
	private String name;
	
	@NotNull
	@Size(min=1, max=1)
	@Pattern(regexp="^[MFU]$")
	private String sex;
	
	@NotNull
	@Past
	private Date dateOfBirth;

}
