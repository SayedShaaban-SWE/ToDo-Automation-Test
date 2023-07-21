package utils.rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;


public class DeleteApiHelper {

    public static Response deleteApi(String apiUrl) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that().header("content-type", "application/json")
                    .when().delete(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;
    }

    public static Response deleteApiByHeaders(String apiUrl, HashMap<String, String> headers) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that()
                    .headers(headers)
                    .when().delete(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;

    }

    public static Response deleteApiByParams(String apiUrl, HashMap<String, String> headers, HashMap<String, String> params) {
        if (ResponseHandler.isNotNullOrEmpty(apiUrl))
            return RestAssured.given().that()
                    .params(params)
                    .when().delete(apiUrl).thenReturn();
        fail(ApiConstants.INVALID_EMPTY_URL_ERROR);
        return null;

    }
}