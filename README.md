#SpringBoot Rest Validation
Basic example to show how to use JSR-303 validation with Spring Boot endpoints.

Note that:

* Request and Response objects are used in all REST communications, there are no lists of parameters.
* Because of above there is not a single object used by all tiers (personal pref)
* Validation annotations are present on the ReadRequest and StoreRequest
* In the controller the method parameters are annotated with @Valid
* In the controller the methods have an extra parameter that Spring injects; BindingResult
* The BindingResult can be checked to see if there are errors and also provides error messages
* Usual resource bundle mechanism can be used to customise messages if necessary
* In application.properties I have fixed the expected date format

This example shows use of JSR-303 validations, though by default I think Spring Boot uses the Hibernate validator so you could use annotations from that as well. Custom validators can be developed and added, but this was just to get you started.

See: http://docs.oracle.com/javaee/6/tutorial/doc/gircz.html

Some example curl commands that will pass muster:

```
curl -v -X POST -H "Content-Type: application/json" -d '{"name":"Mark Meany", "sex":"M", "dateOfBirth": "22/05/1942"}'  http://localhost:8080/people
curl -v -X GET -H "Content-Type: application/json" -d '{"name":"Mark Meany"}'  http://localhost:8080/people/find
curl -v -X GET -H "Content-Type: application/json" -d '{"name":"John St. Abbott"}'  http://localhost:8080/people/find
curl -v -X GET -H "Content-Type: application/json" -d '{"name":"Someone Else"}'  http://localhost:8080/people/find
```

And some that will not:

```
curl -v -X GET -H "Content-Type: application/json" -d '{"name":"SELECT * FROM user;"}'  http://localhost:8080/people/find
curl -v -X POST -H "Content-Type: application/json" -d '{"name":"Mark Meany", "sex":"m", "dateOfBirth": "22/05/1942"}'  http://localhost:8080/people
curl -v -X POST -H "Content-Type: application/json" -d '{"name":"Mark M¢eany", "sex":"M", "dateOfBirth": "22/05/1942"}'  http://localhost:8080/people
```

