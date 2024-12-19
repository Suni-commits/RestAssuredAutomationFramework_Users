package com.Restassured.base;

import com.Restassured.asserts.AssertActions;
import com.Restassured.endpoints.APIConstants;
import com.Restassured.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public ValidatableResponse validatableResponse;
    public JsonPath jsonPath;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public Response response;

    @BeforeTest
    public void setUp(){
        payloadManager=new PayloadManager();
        assertActions=new AssertActions();

        requestSpecification= RestAssured.given()
        .baseUri(APIConstants.Base_url)
        .contentType(ContentType.JSON)
        .log().all();

        assertThat(requestSpecification).isNotNull();

    }

public Integer getUserId(){

        String payload= payloadManager.CreateUsers();
        response=RestAssured.given(requestSpecification)
                .body(payload)
                .post(APIConstants.Create_Update_Url);

        Integer userid=response.jsonPath().getInt("id");

        System.out.println(userid);
        return userid;
    }

}
