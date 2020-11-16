package romanjaik.webloans.component.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
@ConfigurationProperties(prefix = "loan")
public class LoanDefaultSettings {
    @NotNull
    private BigDecimal interest;

    @NotNull
    private BigDecimal factor;

    @NotNull
    private String manager;

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}