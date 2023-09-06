package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;
import org.apache.commons.lang3.RandomStringUtils;

@Endpoint(url = "${base_url}/booking/${id}", methodType = HttpMethodType.PATCH)
@RequestTemplatePath(path = "api/booking/_patch/rq.json")
@ResponseTemplatePath(path = "api/booking/_patch/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PatchBooking extends AbstractApiMethodV2 {

    public PatchBooking(String id, String token) {
        setHeader("Content-Type", "application/json");
        setHeader("Accept", "application/json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
        replaceUrlPlaceholder("id", id);
        addCookie("token", token);
        addProperty("firstName", RandomStringUtils.randomAlphabetic(5));
        addProperty("lastName", RandomStringUtils.randomAlphabetic(5));
    }
}
