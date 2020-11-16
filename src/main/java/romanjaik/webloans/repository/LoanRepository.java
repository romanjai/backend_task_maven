package romanjaik.webloans.repository;

import romanjaik.webloans.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByUserId(String userId);
    Loan findByIdAndUserId(Long id, String userId);
}
