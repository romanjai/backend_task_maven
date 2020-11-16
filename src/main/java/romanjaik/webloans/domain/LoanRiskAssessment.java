package romanjaik.webloans.domain;

import lombok.Getter;
import lombok.Setter;
import romanjaik.webloans.enums.RiskStatus;

@Getter
@Setter
public class LoanRiskAssessment {

    private RiskStatus status;
    private String message;

    public LoanRiskAssessment(RiskStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}