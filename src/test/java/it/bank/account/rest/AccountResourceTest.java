package it.bank.account.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import it.bank.account.domain.vo.Account;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.Stereotype;

import java.util.Collection;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class AccountResourceTest {
    @Test
    void testAllAccount() {
        Response result =
                given()
                .when().get("/accounts")
                .then()
                .statusCode(200)
                .body(
                        containsString("George Baird"),
                        containsString("Mary Taylor"),
                        containsString("Diana Rigg")

                ).extract().response();
        Collection<Account> accounts = result.jsonPath().getList("$");
        assertThat(accounts, is(not(empty())));
        assertThat(accounts, hasSize(3));
    }


}
