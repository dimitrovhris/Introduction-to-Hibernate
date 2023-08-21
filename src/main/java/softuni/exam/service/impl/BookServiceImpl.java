package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement all methods
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(new File("src/main/resources/files/json/books.json").toPath());
    }

    @Override
    public String importBooks() throws IOException {
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        Gson gson = new Gson();

        BookImportDTO[] bookImportDTOS = gson.fromJson(readBooksFromFile(), BookImportDTO[].class);
        for (BookImportDTO book : bookImportDTOS) {
            if(book.isValid(bookRepository)){
                result.add(String.format("Successfully imported book %s - %s", book.getAuthor(), book.getTitle()));
                Book realBook = modelMapper.map(book, Book.class);
                bookRepository.saveAndFlush(realBook);
            } else{
                result.add("Invalid book");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
