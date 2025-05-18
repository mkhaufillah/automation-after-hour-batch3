package scenario;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredE2ETest {
    /*
     * Scenario: RestAssured E2E Test
     * Test case - 001:
     * 1. Test create employee (post) (verify response)
     * 2. Test get employee (get) (verify response)
     * 3. test search employee (get) (verify response)
     * 
     * Test case - 002:
     * 1. Test update employee (put) (verify response)
     * 2. Get employee (get) (verify response)
     * 3. Test search employee (get) (verify response)
     * 
     * Test case - 003:
     * 1. Test delete employee (delete) (verify response)
     * 2. Test get employee (get) (verify response)
     * 
     */

     String token;

    
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
        /* 
         * Kode yang dijalankan sebelum suite
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";
    }
    

    @Test
    public void login() {
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
    public void createEmployee() {
        System.out.println("Test Create Employee");
        /*
         * Kode test untuk test create employee
         * Menggunakan token yang didapat dari beforeSuite
         * Menggunakan RestAssured untuk melakukan request
         * Menggunakan log().all() untuk menampilkan semua request dan response
         * Menggunakan prettyPrint() untuk menampilkan response dengan format yang lebih baik
         * Menggunakan jsonPath() untuk mengambil data dari response
         * Menggunakan assertEquals() untuk membandingkan hasil yang diharapkan dengan hasil yang didapat
         */

        // Create employee
        String json = "{\n" + //
                        "  \"email\": \"albertjuntak13@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\",\n" + //
                        "  \"full_name\": \"Albert Simanjuntak\",\n" + //
                        "  \"department\": \"IT\",\n" + //
                        "  \"title\": \"QA\"\n" + //
                        "}";
        Response response = RestAssured.given()
                            .contentType("application/json")
                            .header("Content-Type", "application/json")
                            .body(json)
                            .log()
                            .all()
                            .when()
                                .post("/webhook/employee/add");
        //do verification
        System.out.println("Response: " + response.asPrettyString());

        // Login
        String jsonLogin = "{\n" + //
                        "  \"email\": \"albertjuntak13@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\"\n" + //
                        "}";

        response = RestAssured.given()
                            .contentType("application/json")
                            .header("Content-Type", "application/json")
                            .body(jsonLogin)
                            .log()
                            .all()
                            .when()
                                .post("/webhook/employee/login");
        token = response.jsonPath().getString("[0].token");

        
        // Get employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get");

        System.out.println("Response get: " + response.asPrettyString());

        // Search employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .pathParam("query", "Albert")
                            .log()
                            .all()
                            .when()
                                .get("/webhook/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/{query}");
        System.out.println("Response search: " + response.asPrettyString());
        
        // do verification
    }


    @Test(dependsOnMethods = "login")
    public void updateEmployee() {
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

        // Update employee
        String bodyUpdate = "{\n" + //
                        "    \"email\": \"afteroffice20@gmail.com\",\n" + //
                        "    \"full_name\": \"afteroffice\",\n" + //
                        "    \"department\": \"science\",\n" + //
                        "    \"title\": \"Biology\",\n" + //
                        "    \"password\" : \"afteroffice123\"\n" + //
                        "}";
        Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .contentType("application/json")
                            .header("Content-Type", "application/json")
                            .body(bodyUpdate)
                            .log()
                            .all()
                            .when()
                                .put("/webhook/employee/update");
        System.out.println("Response: " + response.asPrettyString());
        // do verification

        // Get employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get");
        System.out.println("Response get: " + response.asPrettyString());

        // Search employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .pathParam("query", "Albert")
                            .log()
                            .all()
                            .when()
                                .get("/webhook/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/{query}");
        System.out.println("Response search: " + response.asPrettyString());
        // do verification
    }

    @Test(dependsOnMethods = "login")
    public void deleteEmployee() {
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

        // Delete employee
        Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .delete("/webhook/employee/delete");
        System.out.println("Response: " + response.asPrettyString());
        // do verification

        // Get employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get");
        System.out.println("Response get: " + response.asPrettyString());

        // Search employee
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .pathParam("query", "Albert")
                            .log()
                            .all()
                            .when()
                                .get("/webhook/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/{query}");
        System.out.println("Response search: " + response.asPrettyString());
        // do verification
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
        /* 
         * Kode yang dijalankan setelah suite
         */
        // create employee
        // Create employee
        String json = "{\n" + //
                        "  \"email\": \"albertjuntak13@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\",\n" + //
                        "  \"full_name\": \"Albert Simanjuntak\",\n" + //
                        "  \"department\": \"IT\",\n" + //
                        "  \"title\": \"QA\"\n" + //
                        "}";
        Response response = RestAssured.given()
                            .contentType("application/json")
                            .header("Content-Type", "application/json")
                            .body(json)
                            .log()
                            .all()
                            .when()
                                .post("/webhook/employee/add");
        //do verification
        System.out.println("Response: " + response.asPrettyString());

    }


    @Test(dependsOnMethods = "createEmployee")
    public void deleteEmployeeAfterAdded() {
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

        // Delete employee
        Response response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .delete("/webhook/employee/delete");
        System.out.println("Response: " + response.asPrettyString());
        // do verification
        // Get employee 
        response = RestAssured.given()
                            .header("Authorization", "Bearer " + token)
                            .log()
                            .all()
                            .when()
                                .get("/webhook/employee/get");
        System.out.println("Response get: " + response.asPrettyString());
        //do verification
    }
        
}
