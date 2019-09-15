package com.qa.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import net.minidev.json.JSONArray;

public class APIUtil {

	public static JSONArray getJsonArrayValues(Response response, String key) {
		return com.jayway.jsonpath.JsonPath.parse(response.asString()).read(key);
	}

	public static List<String> getListOfElements(Response response, String key) {
		return com.jayway.jsonpath.JsonPath.parse(response.asString()).read(key);
	}

	public static int getStatusCode(Response resp) {
		return resp.statusCode();
	}

	public Response postCall(String url, String payloadStringified, HashMap<String, String> headers) {
		Response response = null;

		RequestSpecification request = RestAssured.given().config(RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        request.headers(headers);
        response = request.given()
                .body(payloadStringified)
                .when()
                .post(url);
		return response;
	}
	
	public Response getCall(String url, Map<String, String> headers) {
		Response response = null;

		RequestSpecification request = RestAssured.given().config(RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        request.headers(headers);
        response = request.given()
                .when()
                .get(url);
		return response;
	}

}
