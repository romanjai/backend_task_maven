package romanjaik.webloans.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"extensionDate"})
public class LoanExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DateTime extensionDate;

    public Date getDate() {
        if(extensionDate != null) {
            return extensionDate.toDate();
        }

        return null;
    }
}