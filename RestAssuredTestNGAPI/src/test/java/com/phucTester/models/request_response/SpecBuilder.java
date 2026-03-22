package com.phucTester.models.request_response;

import globals.EndPointGlobal;
import globals.TokenGlobal;
import globals.ConfigsGlobal;
import helpers.PropertiesHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder().
            setBaseUri(ConfigsGlobal.URI).
            setBasePath(ConfigsGlobal.PATH).
            addHeader("Authorization", "Bearer " + TokenGlobal.TOKEN).
            setContentType(ContentType.JSON).
            setAccept(ContentType.JSON).
            addFilter(new RequestLoggingFilter()).
            addFilter(new ResponseLoggingFilter()).
            log(LogDetail.ALL).
            build();
    }
    public static String getRequestBody(String filePath, String key) {
        return PropertiesHelper.getJsonValue(filePath, key);
    }

    public static ResponseSpecification getResponseSpecBuilder() {
        return new ResponseSpecBuilder().
            expectContentType(ContentType.JSON).
            log(LogDetail.ALL).
            build();
    }

    public static RequestSpecification getRequestNotAuthSpecBuilder() {
        return new RequestSpecBuilder().
            setBaseUri(ConfigsGlobal.URI).
            setBasePath(ConfigsGlobal.PATH).
            setContentType(ContentType.JSON).
            setAccept(ContentType.JSON).
            addFilter(new RequestLoggingFilter()).
            addFilter(new ResponseLoggingFilter()).
            log(LogDetail.ALL).
            build();
    }
}
