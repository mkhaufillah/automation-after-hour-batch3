package restassured;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredImpl {
    String token;
    RequestSpecification requestSpecification;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
        /* 
         * Kode yang dijalankan sebelum suite
         */
        String json = "{\"email\":\"afteroffice@gmail.com\",\"password\":\"afteroffice123\"}";

        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured.given()
                            .contentType("application/json")
                            .header("Content-Type", "application/json")
                            .body(json)
                            .log()
                            .all()
                            .when()
                                .post("/webhook/employee/login");
        System.out.println("Response: " + response.asPrettyString());
        token = response.jsonPath().getString("[0].token");
        System.out.println("Token: " + token);
    }

    @Test
    public void testGetEmployee() {
        System.out.println("Test Get Employee");
        /*
         * Kode test untuk test get employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

         Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get");

                            
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());

        assert response.getStatusCode() == 200 : "Status code is not 200";
        assert response.jsonPath().getString("[0].full_name").equals("afteroffice12") : "Name is not After Office";
        assert response.jsonPath().getString("[0].email").equals("afteroffice14@gmail.com") : "Email is not afteroffice@gmail.com";
    }

    @Test
    public void testUpdateEmployee() {
        System.out.println("Test Update Employee");
        /*
         * Kode test untuk test update employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

        String bodyUpdate = "{\n" + //
                        "    \"email\": \"afteroffice20@gmail.com\",\n" + //
                        "    \"full_name\": \"afteroffice\",\n" + //
                        "    \"department\": \"science\",\n" + //
                        "    \"title\": \"Biology\",\n" + //
                        "    \"password\" : \"afteroffice123\"\n" + //
                        "}";
         Response response = RestAssured.given()
                            .contentType("application/json; charset=UTF-8")
                            .header("Authorization", "Bearer " + token)
                            .body(bodyUpdate)
                            .log()
                            .all()
                            .when()
                                .put("webhook/employee/update");
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        assert response.getStatusCode() == 200 : "Status code is not 200";
    } 
    
    @Test
    public void testDeleteEmployee() {
        System.out.println("Test Delete Employee");
        /*
         * Kode test untuk test delete employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

        Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .delete("/webhook/employee/delete");
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        assert response.getStatusCode() == 200 : "Status code is not 200";
    }

    @Test
    public void testGetAllEmployee() {
        System.out.println("Test Get All Employee");
        /*
         * Kode test untuk test get all employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

        Response response = RestAssured.given()
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get_all");
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        assert response.getStatusCode() == 200 : "Status code is not 200";
    }

    @Test
    public void searchEmployee() {
        System.out.println("Test Search Employee");
        /*
         * Kode test untuk test search employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

         
        Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .pathParam("query", "andi")
                            .log()
                            .all()
                            .when()
                                .get("/webhook/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/{query}");
        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        assert response.getStatusCode() == 200 : "Status code is not 200";
    }
}
