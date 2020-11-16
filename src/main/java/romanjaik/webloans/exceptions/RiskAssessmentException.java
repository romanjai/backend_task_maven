package romanjaik.webloans.exceptions;

import lombok.Getter;
import lombok.Setter;
import romanjaik.webloans.enums.RiskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RiskAssessmentException extends Exception {
    private RiskStatus status;

    public RiskAssessmentException(RiskStatus status, String message) {
        super(message);
        this.status = status;
    }

    @Override
    public String getMessage() {
        return String.format("%s: %s", status, super.getMessage());
    }
}
