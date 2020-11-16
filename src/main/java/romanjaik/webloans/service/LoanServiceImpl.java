package romanjaik.webloans.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romanjaik.webloans.component.settings.LoanDefaultSettings;
import romanjaik.webloans.domain.Loan;
import romanjaik.webloans.domain.LoanApplication;
import romanjaik.webloans.domain.LoanApprove;
import romanjaik.webloans.domain.LoanExtension;
import romanjaik.webloans.exceptions.RiskAssessmentException;
import romanjaik.webloans.repository.LoanRepository;

import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDefaultSettings settings;

    @Autowired
    private LoanRepository loanRepo;

    @Override
    public Loan applyForLoan(LoanApplication application) throws RiskAssessmentException {

        Loan loan = new Loan();
        loan.setUserId(application.getUserId());
        loan.setAmount(application.getAmount());
        loan.setCustomerId(application.getCustomerId());

        DateTime applicationDate = application.getApplicationDate();
        loan.setApplicationDate(applicationDate);
        loan.setInterest(settings.getInterest());
        loan.setManager_Bulgaria(settings.getManager());

        return loanRepo.save(loan);
    }

    @Override
    public Loan extendLoan(Long loanId, String userId) {
        Loan loan = loanRepo.findByIdAndUserId(loanId, userId);

        LoanExtension extension = new LoanExtension();
        extension.setExtensionDate(DateTime.now());
        loan.setInterest(loan.getInterest().multiply(settings.getFactor()));
        loan.getExtensionHistory().add(extension);

        return loanRepo.save(loan);
    }

    @Override
    public Loan approveLoan(Long loanId, String userId) {

        Loan loan = loanRepo.findByIdAndUserId(loanId, userId);

        LoanApprove approve = new LoanApprove();
        approve.setExtensionDate(DateTime.now());
        loan.setManager_Bulgaria(settings.getManager());


        return loanRepo.save(loan);
    }

    @Override
    public List<Loan> getLoanHistory(String userId) {
        return loanRepo.findByUserId(userId);
    }
}