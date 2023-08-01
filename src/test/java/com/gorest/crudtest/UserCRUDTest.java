package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200); //method type of this is validatable response
    }

@Test
public void verifyUserCreatedSuccessfully(){

    UserPojo userPojo = new UserPojo();

    userPojo.setName("Prime");
    userPojo.setEmail("prime" + getRandomValue() + "@gmail.com");
    userPojo.setGender("male");
    userPojo.setStatus("active");

    Response response = given().log().all()
            .header("Content-Type","application/json; charset=utf-8")
            .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")

            .when()
            .body(userPojo)
            .post();
    response.then().log().all().statusCode(201);
            response.prettyPrint();
}
@Test
public void verifyUserUpdateSuccessfully(){
    String email ="prime" + TestUtils.getRandomValue() + "@gmail.com";

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Testing");
        userPojo.setEmail(email);
    userPojo.setGender("male");
    userPojo.setStatus("active");

    Response response = given().log().all()

            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
            .pathParam("id", 4097103) //passing parameter to .get()
            .when()
            .body(userPojo)
            .patch("/{id}");
    response.then().log().all().statusCode(200);
    response.prettyPrint();
}
@Test
    public void VerifyUserDeleteSuccessfully(){
    Response response = given().log().all()

            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
            .pathParam("id", 4097103) //passing parameter to .get()

            .when()
            .delete("/{id}");

    response.then().log().all().statusCode(204);
    response.prettyPrint();
}


}
