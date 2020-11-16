package romanjaik.webloans.approve;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SuccessfullyExtendLoanTest {

    private String loanId;
    private BigDecimal initialInterest;
    private DateTime initialEndDate;

    @Given("^user has a loan registered$")
    public void user_has_a_loan_registered() {
        String loanJson = get("/rest/loans").asString();
        this.loanId = from(loanJson).getString("[0].id");
        this.initialInterest = new BigDecimal(from(loanJson).getString("[0].interest"));
        this.initialEndDate = new DateTime(new Date(from(loanJson).getLong("[0].termDate")));

        assertThat(loanId, not(nullValue()));
        assertThat(initialInterest, not(nullValue()));
        assertThat(initialEndDate, not(nullValue()));
    }

    @When("^user extends the loan$")
    public void user_extends_loan() {
        given()
                .pathParam("loanId", loanId)
                .when()
                .post("/rest/loans/{loanId}/extend")
                .then()
                .assertThat().statusCode(200);
    }

    @Then("^user history has a loan with an extension registered$")
    public void user_history_has_extended_loan() {
        when()
                .get("/rest/loans")
                .then()
                .body("[0].id", equalTo(Integer.valueOf(loanId)));
    }

    @And("^the loan has it's interest increased by a factor of (.+)$")
    public void extended_loan_has_interest_increased(BigDecimal factor) {
        final BigDecimal extendedInterst = initialInterest.multiply(factor);
        when()
                .get("/rest/loans")
                .then()
                .body("[0].interest", equalTo(extendedInterst.floatValue()));
    }
}
