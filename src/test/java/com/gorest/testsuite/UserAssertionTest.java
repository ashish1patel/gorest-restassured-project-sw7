package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
     //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200); //method type of this is validatable response

    }

    //1. Verify the if the total record is 20
    @Test
    public void test01(){
        response.body("total.size()",equalTo(20));
    }
    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test02(){
        response.body("findAll{it.id == '4040719'}.name",equalTo("Keerti Embranthiri"));
     //response.body("[2].name",equalTo("Keerti Embranthiri"));
    }
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test03(){
        response.body("name",hasItem("Sanya Kaur"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)
    @Test
    public void test04(){
        response.body("name",hasItems("Sanya Kaur","Anish Reddy Sr.","Sarala Menon"));
    }
    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test05(){
        response.body("findAll{it.id == '4040719'}.email",equalTo("embranthiri_keerti@bartell-fritsch.example"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test06(){
        response.body("findAll{it.name == 'Shreyashi Kakkar Sr.'}.status",equalTo("[active]"));

    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test07(){
        response.body("findAll{it.name == 'Shreyashi Kakkar Sr.'}.gender",equalTo("male"));


    }

}
