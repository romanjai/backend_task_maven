package romanjaik.webloans.integration;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import romanjaik.webloans.Launcher;
import romanjaik.webloans.component.settings.LoanDefaultSettings;
//import romanjaik.webloans.component.settings.LoanRiskAssessmentSettings;
import romanjaik.webloans.domain.Loan;
import romanjaik.webloans.domain.LoanApplication;
import romanjaik.webloans.exceptions.RiskAssessmentException;
import romanjaik.webloans.service.LoanService;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Launcher.class)
@Transactional
public class LoanServiceIntegrationTest {


    @Autowired
    private LoanDefaultSettings settings;

    @Autowired
    private LoanService service;

    private LoanApplication application;

    @Before
    public void init() {
        application = mock(LoanApplication.class);
    }

    @Test
    public void testApplyForLoan() throws RiskAssessmentException {
        Money amount = Money.of(CurrencyUnit.EUR, new BigDecimal("100.00"));
        when(application.getAmount()).thenReturn(amount);
        when(application.getUserId()).thenReturn("USER_ID");

        DateTime applicationDate = DateTime.now().withTime(13, 1, 1, 1);
        when(application.getApplicationDate()).thenReturn(applicationDate);

        Loan loan = service.applyForLoan(application);

        assertThat(loan.getId(), not(nullValue()));
        assertThat(loan.getAmount(), equalTo(amount));
        assertThat(loan.getApplicationDate(), equalTo(applicationDate));
        assertThat(loan.getEndDate(), equalTo(applicationDate.plusDays(120)));
        assertThat(loan.getInterest(), equalTo(settings.getInterest()));
    }
}
