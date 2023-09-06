package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;
import org.apache.commons.lang3.RandomStringUtils;

@Endpoint(url = "${base_url}/booking/${id}", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/booking/_put/rq.json")
@ResponseTemplatePath(path = "api/booking/_put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@PropertiesPath(path = "api/booking/_post/booking.properties")
public class PutBooking extends AbstractApiMethodV2 {

    public PutBooking(String id, String token) {
        setHeader("Content-Type", "application/json");
        setHeader("Accept", "application/json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
        replaceUrlPlaceholder("id", id);
        addCookie("token", token);
        addProperty("newPrice", Integer.parseInt(RandomStringUtils.randomNumeric(4)));
    }
}
