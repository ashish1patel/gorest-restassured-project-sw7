package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class PostsAssertionTest {

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

    //1. Verify the if the total record is 25
    @Test
    public void test01(){
        response.body("total.size()",equalTo(25));
    }
    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test02(){
       // response.body("findAll{it.id == ' 56979'}.title",equalTo("Summa abduco quae blanditiis deorsum."));
        response.body("findAll{it.id == 56979}", hasItem(hasEntry("title","Summa abduco quae blanditiis deorsum.")));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test03(){
        response.body("id",hasItem(56978));

    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test04(){
        response.body("id",hasItems(56978,56969,56965));

    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void test05(){

    }
}
