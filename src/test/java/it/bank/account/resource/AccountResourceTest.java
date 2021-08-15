package it.bank.account.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import it.bank.account.domain.enumerator.AccountStatus;
import it.bank.account.domain.vo.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountResourceTest {
    @Test
    @Order(1)
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

    @Test
    @Order(2)
    void testGetAccount(){
        Account account =
                given()
                .when().get("/accounts/{accountNumber}", 123456789 )
                .then()
                .statusCode(200)
                .extract()
                .as(Account.class);

        assertThat(account.getAccountNumber(), equalTo(123456789L));
        assertThat(account.getCustomerName(), equalTo("George Baird"));
        assertThat(account.getAccountStatus(), equalTo(AccountStatus.OPEN));
        assertThat(account.getBalance(), equalTo(new BigDecimal("354.23")));
    }

    @Test
    @Order(3)
    void testCreateAccount(){
        Account newAccount = new Account(324324L, 112244L, "Sandy Holmes",
                new BigDecimal("154.55"));

        Account returnedAccount =
                given()
                .contentType(ContentType.JSON)
                .body(newAccount)
                .when().post("/accounts")
                .then()
                .statusCode(201)
                .extract()
                .as(Account.class);
        assertThat(returnedAccount, equalTo(newAccount));

        Response result =
                given()
                .when().get("/accounts")
                .then()
                .statusCode(200)
                .body(
                        containsString("George Baird"),
                        containsString("Mary Taylor"),
                        containsString("Diana Rigg"),
                        containsString("Sandy Holmes")
                ).extract().response();

        List<Account> accounts = result.jsonPath().getList("$");
        assertThat(accounts, is(not(empty())));
        assertThat(accounts, hasSize(4));

    }


}
