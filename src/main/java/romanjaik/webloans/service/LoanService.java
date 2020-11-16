package romanjaik.webloans.service;

import romanjaik.webloans.domain.Loan;
import romanjaik.webloans.domain.LoanApplication;
import romanjaik.webloans.exceptions.RiskAssessmentException;

import java.util.List;

public interface LoanService {

    Loan applyForLoan(LoanApplication application) throws RiskAssessmentException;

    Loan extendLoan(Long loanId, String userId);

    Loan approveLoan(Long loanId, String userId);

    List<Loan> getLoanHistory(String userId);
}
