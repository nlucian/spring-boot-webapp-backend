package com.practice.rest.webservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ####
 * When you talk about versioning you must take into consideration:
 * <p>
 * URI Pollution
 * <p>
 * Misuse of HTTP Headers (http headers were never intended for versioning)
 * <p>
 * Caching (becomes difficult when we use headers.. complicated checks are required)
 * <p>
 * Can we execute the request on the browser?
 * <p>
 * API Documentation - for Media type versioning and header type versioning documenting can be difficult.
 * <p>
 * ######
 *
 * For  Media type versioning and headers versioning
 * a plugin must be used or the client won't be able to differentiate the versions.
 */
@RestController
public class PersonVersioningController {

    //basic versioning -- URI versioning
    //used by Twitter
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charle");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //param versioning -- Request Param Versioning
    //used by Amazon
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Bob Charle");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //header versioning
    //used by Microsoft
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Bob Charle");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //produces versioning -- also called mime type versioning
    //a.k.a 'content negotiation' or 'accept header'
    //used by GitHub
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charle");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
