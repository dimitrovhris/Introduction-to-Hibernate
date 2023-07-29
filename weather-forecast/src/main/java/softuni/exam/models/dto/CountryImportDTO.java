package softuni.exam.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryImportDTO implements Serializable {
    private String countryName;
    private String currency;

    public boolean isValid() {
        if(this.countryName.length() < 2 || this.countryName.length() > 60){
            return false;
        }
        if(currency.length() < 2 || currency.length() > 20){
            return false;
        }
        return true;
    }
}
