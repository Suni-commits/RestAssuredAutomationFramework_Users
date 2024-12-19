package com.Restassured.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {


    public static void verifyResponseBody(String actual,String expected,String Description){
        assertEquals(actual, expected, Description);
    }

    public static void verifyResponseBody(String actual,int expected,String Description){
        assertEquals(actual, expected, Description);
    }

    public static void verifyStatusCode(Response response,Integer expected){
        assertEquals(response,expected);
    }

}
