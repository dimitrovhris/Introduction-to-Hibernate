package softuni.exam.models.dto;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.repository.LibraryMemberRepository;

@Getter
@Setter
public class LibraryMemberImportDTO {

    private String address;

    private String firstName;

    private String lastName;

    private String phoneNumber;


    public boolean isValid(LibraryMemberRepository libraryMemberRepository) {
        if (firstName != null && lastName != null && phoneNumber != null && firstName.length() >= 2 && firstName.length() <= 30
                && lastName.length() >= 2 && lastName.length() <= 30
                && phoneNumber.length() >= 2 && phoneNumber.length() <= 20
                && libraryMemberRepository.findFirstByPhoneNumber(phoneNumber).isEmpty()) {
            if(address != null){
                if(address.length() >= 2 && address.length() <= 40){
                    return true;
                } else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
