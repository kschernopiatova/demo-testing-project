package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;

@Endpoint(url = "${base_url}/booking", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/booking/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetBookings extends AbstractApiMethodV2 {

    public GetBookings() {
        setHeader("Content-Type", "application/json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
    }

}
