package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.annotation.method.*;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@EndpointTemplate(url = "${config.api_url}/booking")
@Header(key= "Content-Type", value = "application/json")
@Header(key= "Accept", value = "application/json")
public interface BookingTemplate {

    @GetMethod(url = "/")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getAll();

    @GetMethod(url = "/${id}")
    @ResponseTemplatePath(path = "api/booking/_get/rs.json")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getById(@PathParam(key = "id") String id);


    @DeleteMethod(url = "/${id}")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
    AbstractApiMethodV2 deleteById(@PathParam(key = "id") String id);

    @PatchMethod(url = "/${id}")
    @RequestTemplatePath(path = "api/booking/_patch/rq.json")
    @ResponseTemplatePath(path = "api/booking/_patch/rs.json")
    @PropertiesPath(path = "api/booking/_patch/booking.properties")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 patchById(@PathParam(key = "id") String id);

    @PostMethod(url = "/")
    @RequestTemplatePath(path = "api/booking/_post/rq.json")
    @ResponseTemplatePath(path = "api/booking/_post/rs.json")
    @PropertiesPath(path = "api/booking/_post/booking.properties")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 post();

    @PutMethod(url = "/${id}")
    @RequestTemplatePath(path = "api/booking/_put/rq.json")
    @ResponseTemplatePath(path = "api/booking/_put/rs.json")
    @PropertiesPath(path = "api/booking/_post/booking.properties")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 putById(@PathParam(key = "id") String id);

}
