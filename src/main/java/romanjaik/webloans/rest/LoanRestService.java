package romanjaik.webloans.rest;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanjaik.webloans.component.producer.DateTimeProducer;
import romanjaik.webloans.domain.Loan;
import romanjaik.webloans.domain.LoanApplication;
import romanjaik.webloans.exceptions.RiskAssessmentException;
import romanjaik.webloans.service.LoanService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping(value = "/rest/loans")
public class LoanRestService {

    @Autowired
    private DateTimeProducer dateTimeProducer;

    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Loan> getLoanHistory(HttpServletRequest request) {
       final String ip = request.getRemoteAddr();

       return loanService.getLoanHistory(ip);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> applyForLoan(@RequestParam("amount") BigDecimal amount,
                                               @RequestParam("customerId") String customerId,
                                               HttpServletRequest request) throws RiskAssessmentException {

        final String ip = request.getRemoteAddr();

        LoanApplication application = new LoanApplication();
        application.setAmount(Money.of(CurrencyUnit.EUR, amount));
        application.setApplicationDate(dateTimeProducer.getCurrentDateTime());
        application.setCustomerId(customerId);
        application.setUserId(ip);

        loanService.applyForLoan(application);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value="/{loanId}/extend", method = RequestMethod.POST)
    public ResponseEntity<String> extendLoan(@PathVariable("loanId") Long loanId,
                           HttpServletRequest request) {
        final String ip = request.getRemoteAddr();
        loanService.extendLoan(loanId, ip);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value="/{loanId}/approve", method = RequestMethod.POST)
    public ResponseEntity<String> approveLoan(@PathVariable("loanId") Long loanId,
                                            @PathVariable("manager_Bulgaria") String manager_Bulgaria,
                                             HttpServletRequest request) {
        final String ip = request.getRemoteAddr();
        loanService.approveLoan(loanId, ip);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


}