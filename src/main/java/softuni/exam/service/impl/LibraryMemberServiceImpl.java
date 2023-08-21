package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberImportDTO;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement all methods
@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private LibraryMemberRepository libraryMemberRepository;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository) {
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(new File("src/main/resources/files/json/library-members.json").toPath());
    }

    @Override
    public String importLibraryMembers() throws IOException {
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        Gson gson = new Gson();

        LibraryMemberImportDTO[] libraryMemberImportDTOS = gson.fromJson(readLibraryMembersFileContent(), LibraryMemberImportDTO[].class);
        for (LibraryMemberImportDTO libraryMember : libraryMemberImportDTOS) {
            if(libraryMember.isValid(libraryMemberRepository)){
                result.add(String.format("Successfully imported library member %s - %s", libraryMember.getFirstName(), libraryMember.getLastName()));
                LibraryMember realLibraryMember = modelMapper.map(libraryMember, LibraryMember.class);
                libraryMemberRepository.saveAndFlush(realLibraryMember);
            } else{
                result.add("Invalid library member");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
