package com.mvmlabs.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SampleController {

	private Map<String, Person> cache = new ConcurrentHashMap<>();

	private ObjectMapper om = new ObjectMapper();
	
	@GetMapping(value="/people/find")
	public ResponseEntity<ReadResponse> read(@Valid @RequestBody final ReadRequest request, final BindingResult binding) {
		val response = new ReadResponse();
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		if (binding.hasErrors()) {
			String erm;
			try {
				erm = om.writeValueAsString(binding.getAllErrors());
			} catch (JsonProcessingException e) {
				erm = "JACKSON FAILED";
			}
			log.warn("Validation errors encountered: {}", erm);
			status = HttpStatus.BAD_REQUEST;
		} else {
			// All valid, yay!
			if (cache.containsKey(request.getName())) {
				response.setPerson(cache.get(request.getName()));
				response.setSuccess(true);
				status = HttpStatus.OK;
			}
		}

		
		return new ResponseEntity<ReadResponse>(response, status);
	}

	@PostMapping(value="/people")
	public ResponseEntity<StoreResponse> store(@Valid @RequestBody final StoreRequest request, final BindingResult binding) {
		val response = new StoreResponse();
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		if (binding.hasErrors()) {
			String erm;
			try {
				erm = om.writeValueAsString(binding.getAllErrors());
			} catch (JsonProcessingException e) {
				erm = "JACKSON FAILED";
			}
			log.warn("Validation errors encountered: {}", erm);
			response.setErrorMessage(erm);
			status = HttpStatus.BAD_REQUEST;
		} else {
			// All valid, yay!
			Person person = new Person();
			person.setName(request.getName());
			person.setSex(request.getSex());
			person.setDateOfBirth(request.getDateOfBirth());
			cache.put(person.getName(), person);
			response.setSuccess(true);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<StoreResponse>(response, status);
	}
}
