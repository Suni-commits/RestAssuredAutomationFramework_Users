package com.Restassured.Tests.Integration.CRUD;

import com.Restassured.asserts.AssertActions;
import com.Restassured.base.BaseTest;
import com.Restassured.endpoints.APIConstants;
import com.Restassured.pojos.Users;
import com.Restassured.pojos.UsersResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationFlow extends BaseTest {

    @Owner("Suneetha")
    @Description("TC#01 : Verifying the creation of users")
    @Test(groups="Integration",priority=1)
    void test_CreatenewUser(ITestContext context){

        response= RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.CreateUsers())
                .post(APIConstants.Create_Update_Url);

    validatableResponse=response.then().log().all();
    validatableResponse.statusCode(201);

        UsersResponse usersResponse= payloadManager.usersResponse(response.asString());

        context.setAttribute("userid",usersResponse.getId());
    }

    @Owner("Suneetha")
    @Description("TC#02 : Verifying the single user details after creation")
    @Test(groups="Integration",priority=2)
    void test_getUserDetails(ITestContext context){
        Integer userid=(Integer)context.getAttribute("userid");
response=RestAssured.given(requestSpecification)
        .basePath(APIConstants.Create_Update_Url +"/" +userid)
        .get();
validatableResponse=response.then().log().all();
validatableResponse.statusCode(200);

        UsersResponse usersResponse=payloadManager.usersResponse(response.asString());
assertThat(usersResponse.getId()).isNotZero().isNotNegative();
assertThat(usersResponse.getName()).isNotEmpty().isNotNull();
assertThat(usersResponse.getName()).isEqualTo("Mohan Siva");

    }

    @Owner("Suneetha")
    @Description("TC#03 : Verifying updation of user details")
    @Test(groups="Integration",priority=3)
    void test_UpdateUserDetails(ITestContext context){
        Integer userid=(Integer)context.getAttribute("userid");
        response=RestAssured.given(requestSpecification)
                .body(payloadManager.UpdateUserDetails())
                .basePath(APIConstants.Create_Update_Url +"/" +userid)
                .put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        UsersResponse usersResponse=payloadManager.usersResponse(response.asString());
        assertThat(usersResponse.getId()).isNotZero().isNotNegative();
        assertThat(usersResponse.getName()).isNotEmpty().isNotNull();
        assertThat(usersResponse.getName()).isEqualTo("Sadhasivam");
        assertThat(usersResponse.getPassword()).isEqualTo("password12234");
        assertThat(usersResponse.getRole()).isEqualTo("customer");
        assertThat(usersResponse.getEmail()).isEqualTo("sada1234@example.com");


    }


    @Owner("Suneetha")
    @Description("TC#04 : Verifying user details after updation of payload")
    @Test(groups="Integration",priority=4)
    void updatedSingleUser(ITestContext context){
        Integer userid=(Integer)context.getAttribute("userid");
        response=RestAssured.given(requestSpecification)
                .basePath(APIConstants.Create_Update_Url +"/" +userid)
                .get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);


    }

    @Owner("Suneetha")
    @Description("TC#05 : Verifying deletion of user details")
    @Test(groups="Integration",priority=5)
    void test_deletionUserDetails(ITestContext context){
        Integer userid=(Integer)context.getAttribute("userid");
        response=RestAssured.given(requestSpecification)
                .basePath(APIConstants.Create_Update_Url +"/" +userid)
                .delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);


    }




}
