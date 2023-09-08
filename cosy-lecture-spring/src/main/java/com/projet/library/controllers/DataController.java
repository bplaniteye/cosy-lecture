package com.projet.library.controllers;

import com.projet.library.datacreation.DataTables;
import com.projet.library.entities.*;
import com.projet.library.enums.Role;
import com.projet.library.repositories.*;
import com.projet.library.services.BorrowService;
import net.datafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Locale;
//import java.util.concurrent.TimeUnit;
//import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;

@Controller
public class DataController {

    Faker faker = new Faker(new Locale("fr"));

    public final AuthorRepository authorRepository;
    public final BookRepository bookRepository;
    public final BorrowRepository borrowRepository;
    public final CategoryRepository categoryRepository;
    public final EditorRepository editorRepository;
    public final LibraryUserRepository userRepository;
    public final NationalityRepository nationalityRepository;
    public final PictureRepository pictureRepository;
    //public final RoleRepository roleRepository;
    public final BorrowService borrowService;

    public DataController(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            BorrowRepository borrowRepository,
            CategoryRepository categoryRepository,
            EditorRepository editorRepository,
            LibraryUserRepository userRepository,
            NationalityRepository nationalityRepository,
            PictureRepository pictureRepository,
            BorrowService borrowService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.categoryRepository = categoryRepository;
        this.editorRepository = editorRepository;
        this.userRepository = userRepository;
        this.nationalityRepository = nationalityRepository;
        this.pictureRepository = pictureRepository;
        // this.roleRepository = roleRepository;
        this.borrowService = borrowService;
    }

    @GetMapping("/dataset")
    public String createData() {
        // Creation of countries
        for (int co = 0; co < DataTables.countries.length; co++) {
            NationalityEntity country = new NationalityEntity();
            country.setName(DataTables.countries[co]);
            nationalityRepository.save(country);
        }

        //Creation of editors
        for (int e = 0; e < 25; e++) {
            EditorEntity editor = new EditorEntity();
            editor.setName(faker.book().publisher());
            editorRepository.save(editor);
        }

        // Creation of categories
        for (int ca = 0; ca < DataTables.categories.length; ca++) {
            CategoryEntity category = new CategoryEntity();
            category.setLabel(DataTables.categories[ca]);
            category.setDefinition(faker.lorem().sentence());
            categoryRepository.save(category);
        }

        // // Creation of admins
        // for (int a = 0; a < DataTables.firstnames.length; a++) {
        //     LibraryUserEntity admin = new LibraryUserEntity();
        //     admin.setFirstName(DataTables.firstnames[a]);
        //     admin.setLastName(DataTables.lastnames[a]);
        //     admin.setPhoneNumber(faker.phoneNumber().cellPhone());
        //     admin.setEmail(admin.getFirstName().toLowerCase() + "." + admin.getLastName().toLowerCase()
        //             + "@library-les-loubards.fr");
        //     admin.setPassword(faker.internet().password());
        //     admin.setAddress(faker.address().fullAddress());
        //     admin.setBirthday(faker.date().birthday().toLocalDateTime());
        //     admin.setRole(roleRepository.findById(1).get());
        //     userRepository.save(admin);
        //     PictureEntity picture = new PictureEntity();
        //     picture.setUrl(faker.internet().image());
        //     picture.setName(admin.getFirstName() + "_" + admin.getLastName());
        //     picture.setDescription("Photo de " + admin.getFirstName() + " " + admin.getLastName());
        //     picture.setUser(admin);
        //     pictureRepository.save(picture);        //
        // }

        // Creation of users
        for (int u = 0; u < 100; u++) {
            LibraryUserEntity user = new LibraryUserEntity();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPhoneNumber(faker.phoneNumber().cellPhone());
            user.setEmail(user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase() + "@gmail.com");
            user.setPassword(faker.internet().password());
            user.setAddress(faker.address().fullAddress());
            user.setBirthday(faker.date().birthday().toLocalDateTime().toLocalDate());
             user.setRole(Role.ROLE_USER);
            userRepository.save(user);
            PictureEntity picture = new PictureEntity();
            picture.setUrl(faker.internet().image());
            picture.setName(user.getFirstName() + "_" + user.getLastName());
            picture.setDescription("Photo de " + user.getFirstName() + " " + user.getLastName());
            picture.setUser(user);
            pictureRepository.save(picture);
        }

        // Creation of authors
        for (int k = 0; k < 50; k++) {
            AuthorEntity author = new AuthorEntity();
            author.setFirstname(faker.name().firstName());
            author.setLastname(faker.name().lastName());
            author.setBio(faker.lorem().sentence());
            author.setBirthday(faker.date().past(36500, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
            author.setDeathday(faker.date().birthday().toLocalDateTime().toLocalDate());
            authorRepository.save(author);
            int randomAP = faker.number().numberBetween(1, 5);
            for (int j = 0; j < randomAP; j++) {
                PictureEntity picture = new PictureEntity();
                picture.setUrl(faker.internet().image());
                picture.setName(author.getFirstname() + "_" + author.getLastname() + "_" + j);
                picture.setDescription("Photo de " + author.getFirstname() + " " + author.getLastname() + " " + j);
                picture.setAuthor(author);
                pictureRepository.save(picture);
            }
            int randomAN = faker.number().numberBetween(1, 3);
            for (int an = 0; an < randomAN; an++) {
                author.setNationalityCollection(nationalityRepository.findAllById(
                        Collections.singleton(faker.number().numberBetween(1, DataTables.countries.length))));
            }
        }

        // Creation of books
        for (int b = 0; b < 100; b++) {
            BookEntity book = new BookEntity();
            book.setTitle(faker.book().title());
            book.setSummary(faker.lorem().paragraph());
            book.setQuantity(faker.number().numberBetween(1, 10));
            book.setEditor(editorRepository.findById(faker.number().numberBetween(1, 25)).get());
            book.setAvailable(true);
            book.setVersion("Français");
            book.setPublicationYear(faker.date().birthday());
            book.setAuthor(authorRepository.findById(faker.number().numberBetween(1, 50)).get());
            book.setCategory(categoryRepository.findById(faker.number().numberBetween(1, DataTables.categories.length)).get());
            bookRepository.save(book);
            int randomBP = faker.number().numberBetween(1, 5);
            for (int j = 0; j < randomBP; j++) {
                PictureEntity picture = new PictureEntity();
                picture.setUrl(faker.internet().image());
                picture.setName(book.getTitle());
                picture.setDescription("Photo du livre : " + book.getTitle());
                picture.setBook(book);
                pictureRepository.save(picture);
            }
        }

        Iterable<BookEntity> books = bookRepository.findAll();
        Iterable<AuthorEntity> authors = authorRepository.findAll();
        Iterator<AuthorEntity> authorIterator = authors.iterator();
        for (BookEntity book : books) {
            if (authorIterator.hasNext()) {
                AuthorEntity author = authorIterator.next();
                book.setAuthor(author);
                bookRepository.save(book);
            }
        }

        // Creation of borrows
    //     for (int n = 0; n < 50; n++) {
    //         BorrowEntity borrow = new BorrowEntity();
    //         borrow.setUser(userRepository.findById(faker.number().numberBetween(4, 104)).get());
    //         borrow.setBook(bookRepository.findById(faker.number().numberBetween(1, 100)).get());
    //         borrow.setStartDate(faker.date().past(30, TimeUnit.DAYS).toLocalDateTime());
    //         borrow.setEndDate(faker.date().future(30, TimeUnit.DAYS).toLocalDateTime());
    //         //borrow.setPenalty(borrowService.calculatePenalty(borrow));
    //         borrow.setPenalty(10.5f);
    //         borrowRepository.save(borrow);
    //     }
        return "redirect:index.html";
    }

}
