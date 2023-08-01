package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test01(){
        List<String> title = response.extract().path("title");
        System.out.println(title);

    }
    //2. Extract the total number of record
    @Test
    public void test02(){
        List<String> size = response.extract().path("");
        System.out.println(size.size());
    }
    //3. Extract the body of 15th record
    @Test
    public void test03(){
       String body = response.extract().path("[14].body");
        System.out.println(body);
    }
    //4. Extract the user_id of all the records
    @Test
    public void test04(){
List<Integer> ids = response.extract().path("id");
        System.out.println(ids);
    }
    //5. Extract the title of all the records
    @Test
    public void test05(){
        List<String> titles = response.extract().path("title");
        System.out.println(titles);

    }
    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test06(){
        List<HashMap<?,?>> title = response.extract().path("findAll{it.user_id == '4040713'}.title");
        System.out.println(title);
    }
    //7. Extract the body of all records whose id = 2671
    @Test
    public void test07(){
        List<String> body = response.extract().path("findAll{it.id == '56978'}.body");
        System.out.println(body);
    }
    }

