package com.practice.rest.webservices.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FilteringController {

    @GetMapping(path = "/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value2");
    }

    @GetMapping(path = "/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering() {
        DynamicBean dynamicBean = new DynamicBean("v1", "v2", "v3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("someBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(dynamicBean);
        mapping.setFilters(filters);

        return mapping;
    }
}
