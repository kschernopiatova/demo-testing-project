package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.annotation.method.PostMethod;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@EndpointTemplate(url = "${config.api_url}/auth")
@Header(key= "Content-Type", value = "application/json")
public interface TokenTemplate {

    @PostMethod(url = "/")
    @RequestTemplatePath(path = "api/token/rq.json")
    @ResponseTemplatePath(path = "api/token/rs.json")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 create();
}
