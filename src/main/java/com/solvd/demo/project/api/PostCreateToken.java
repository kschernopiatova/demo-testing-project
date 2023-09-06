package com.solvd.demo.project.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;

@Endpoint(url = "${base_url}/auth", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/token/rq.json")
@ResponseTemplatePath(path = "api/token/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostCreateToken extends AbstractApiMethodV2 {

    public PostCreateToken() {
        setHeader("Content-Type", "application/json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
    }
}
