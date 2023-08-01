package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test01(){
       List<Integer> allIds = response.extract().path("id");
        System.out.println(allIds);
    }
    //2. Extract the all Names
    @Test
    public void test02(){
        List<String> allNames = response.extract().path("name");
        System.out.println(allNames);
    }
    //3. Extract the name of 5th object
    @Test
    public void test03(){
        String name = response.extract().path("[4].name");
        System.out.println(name);
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test04(){
        List<String> nameStatus = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println(nameStatus);
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test05(){
        List<String> nameOfGender = response.extract().path("findAll{it.status == 'inactive'}.gender");
        System.out.println(nameOfGender);
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test06(){
        List<String> nameOfGender = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println(nameOfGender);
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test07(){
        List<String> nameOfEmail = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println(nameOfEmail);
    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test08(){
        List<String> nameOfId = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println(nameOfId);
    }
    //9. Get all the status
    @Test
    public void test09(){
        List<String> allStatus = response.extract().path("status");
        System.out.println(allStatus);
    }
    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test10(){
        List<String> getEmail = response.extract().path("findAll{it.name == 'Keerti Embranthiri'}.email");
        System.out.println(getEmail);
    }
    //11. Get gender of id = 5471
    @Test
    public void test11(){
//        List<HashMap<?,?>> getGender = response.extract().path("findAll{it.id == '4040715'}.gender");
//        System.out.println(getGender);
        List<String> gender= response.extract().path("findAll{it.id == 4040709}.gender");
        System.out.println(gender);
    }

}
