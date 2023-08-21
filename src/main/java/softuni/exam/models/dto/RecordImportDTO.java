package softuni.exam.models.dto;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.models.entity.Book;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Member;
import java.util.Set;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class RecordImportDTO {

    @XmlElement(name = "borrow_date")
    private String borrowDate;

    @XmlElement(name = "return_date")
    private String returnDate;

    @XmlElement(name = "book")
    private RecordGetBookTitle book;

    @XmlElement(name = "member")
    private RecordGeMemberIdDTO member;

    @XmlElement(name = "remarks")
    private String remarks;

    public boolean isValid() {
        if(remarks != null && remarks.length() >= 3 && remarks.length() <= 100 && borrowDate != null && returnDate != null){
            return true;
        } else if(remarks == null && borrowDate != null && returnDate != null){
            return true;
        }
        return false;
    }
}
