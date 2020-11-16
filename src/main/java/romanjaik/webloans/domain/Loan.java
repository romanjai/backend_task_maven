package romanjaik.webloans.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"amount", "applicationDate", "endDate"})
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String customerId;

    private String manager_Bulgaria;

    private String manager_Moldova;

    private String manager_Latvia;

    private Money amount;

    private Money count_amount;

    private Money avr_amount;

    private Money sum_amount;

    private BigDecimal interest;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime applicationDate;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanExtension> extensionHistory;


    public BigDecimal getLoanAmount() {
       if(amount != null) {
           return amount.getAmount();
        }

        return null;
    }

    public Date getLoanDate() {
        if(applicationDate != null) {
            return applicationDate.toDate();
        }

           return null;
       }


}
