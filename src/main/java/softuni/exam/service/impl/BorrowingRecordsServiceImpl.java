package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.enums.Genre;
import softuni.exam.models.dto.RecordImportDTO;
import softuni.exam.models.dto.RecordImportWrapperDTO;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// TODO: Implement all methods
@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private LibraryMemberRepository libraryMemberRepository;

    private XmlParser xmlParser;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, XmlParser xmlParser) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(new File("src/main/resources/files/xml/borrowing-records.xml").toPath());
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        RecordImportWrapperDTO recordImportWrapperDTO = xmlParser.fromFile(Path.of("src/main/resources/files/xml/borrowing-records.xml").toFile(), RecordImportWrapperDTO.class);
        List<RecordImportDTO> recordImportDTOS = recordImportWrapperDTO.getRecordImportDTOs();
        for (RecordImportDTO record : recordImportDTOS) {
            String bookTitle = record.getBook().getTitle();
            Long memberId = record.getMember().getId();
            if (record.isValid() && bookRepository.findFirstByTitle(bookTitle).isPresent() && libraryMemberRepository.findFirstById(memberId).isPresent()) {
                result.add(String.format("Successfully imported borrowing record %s - %s", bookTitle, record.getBorrowDate()));
                BorrowingRecord realRecord = modelMapper.map(record, BorrowingRecord.class);
                realRecord.setBorrowDate(LocalDate.parse(record.getBorrowDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                realRecord.setReturnDate(LocalDate.parse(record.getReturnDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                realRecord.setMember(libraryMemberRepository.findFirstById(memberId).get());
                realRecord.setBook(bookRepository.findFirstByTitle(bookTitle).get());
                borrowingRecordRepository.saveAndFlush(realRecord);
            } else {
                result.add("Invalid borrowing record");
            }
        }
        return String.join(System.lineSeparator(), result);
    }

    @Override
    public String exportBorrowingRecords() {
        List<String> result = new ArrayList<>();
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findAllByOrderByBorrowDateDesc();
        for (BorrowingRecord record : borrowingRecords) {
            if(record.getBook().getGenre().equals(Genre.SCIENCE_FICTION)){
                result.add(String.format("Book title: %s%n*Book author: %s%n**Date borrowed: " + record.getBorrowDate() + "%n***Borrowed by: %s %s", record.getBook().getTitle(), record.getBook().getAuthor(), record.getMember().getFirstName(), record.getMember().getLastName()));
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
