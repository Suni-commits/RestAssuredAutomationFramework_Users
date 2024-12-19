package com.Restassured.Tests.Integration.Sample;

import com.Restassured.base.BaseTest;
import com.Restassured.endpoints.APIConstants;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class VerifyCreaitonofUser extends BaseTest {

    @Test
    void test_CreatenewUser(){

        response= RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.CreateUsers())
                .post(APIConstants.Create_Update_Url);

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }
}
