package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;

@Endpoint(url = "${base_url}/booking", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/booking/_post/rq.json")
@ResponseTemplatePath(path = "api/booking/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@PropertiesPath(path = "api/booking/_post/booking.properties")
public class PostBooking extends AbstractApiMethodV2 {

    public PostBooking() {
        setHeader("Content-Type", "application/json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
    }
}
