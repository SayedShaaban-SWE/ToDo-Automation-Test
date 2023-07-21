package utils.rest_assured;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;


public class ResponseHandler {

    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getStatusLine(Response response) {
        return response.getStatusLine();
    }

    public static long getResponseTime(Response response) {
        return response.getTime();
    }

    public static HashMap getResponseBody(Response response) {
        if (isNotNullOrEmpty(response))
            return response.getBody().jsonPath().get();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;

    }

    public static Object getResponseBodyByKey(Response response, String key) {
        if (isNotNullOrEmpty(response))
            return response.getBody().jsonPath().get(key);
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }

    public static Headers getResponseHeaders(Response response) {
        return response.getHeaders();
    }

    public static String getResponseHeadersByID(Response response, String headerName) {
        if (isNotNullOrEmpty(response))
            return response.getHeader(headerName);
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }

    public static boolean isNotNullOrEmpty(Object param) {
        return !(param == null || param.toString().isEmpty());
    }
}