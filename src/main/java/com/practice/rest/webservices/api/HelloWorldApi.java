package com.practice.rest.webservices.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello-world")
public class HelloWorldApi {

    private static final String HELLO_WORLD = "Hello World!";

    @Autowired
    //@Qualifier("msgResources")
    private MessageSource messageSource;

    @GetMapping
    public String helloWorld() {
        return HELLO_WORLD;
    }

    /*
    @GetMapping(path = "/i18n")
    public String helloWorldI18N(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
    */

    // you can get the locale from the LocaleContextHolder -> no need to pass it as a param anymore as in the previous method
    @GetMapping(path = "/i18n")
    public String helloWorldI18N() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
