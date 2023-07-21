package utils.rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;


public class GetApiHelper {

    public static Response getResponse(String apiUrl) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that().when().get(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }

    public static Response getResponseByHeaders(String apiUrl, HashMap<String, String> headers) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that().headers(headers).when().get(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }

    public static Response getResponseByHeadersAndParams(String apiUrl, HashMap<String, String> headers, HashMap<String, String> params) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that().headers(headers).params(params).when().get(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;

    }

    public static Response getResponseByParams(String apiUrl, HashMap<String, String> params) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that().params(params).when().get(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }
}
