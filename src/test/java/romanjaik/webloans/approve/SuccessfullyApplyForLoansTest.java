package romanjaik.webloans.approve;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.math.BigDecimal;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

public class SuccessfullyApplyForLoansTest {


        private BigDecimal amount;
        private Integer days;

        @Given("^user wants a loan of EUR (\\d+) for (\\d+) days$")
        public void user_wants_loan(BigDecimal amount, Integer days) {
            this.amount = amount;
            this.days = days;
        }


        @Then("^user has a new loan in his loan history$")
        public void user_has_new_loan_in_history(){
            when()
                    .get("/rest/loans")
                    .then()
                    .body("[0]", not(nullValue()));
        }

    }

