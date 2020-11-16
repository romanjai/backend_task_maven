package romanjaik.webloans.domain;



import lombok.Getter;
import lombok.Setter;
import org.joda.money.Money;
import org.joda.time.DateTime;



@Getter
@Setter
public class LoanApplication {

    private String userId;
    private Money amount;
    private String customerId;
    private DateTime applicationDate;
    private Money count;
    private Money sum;
    private Money avr;

}