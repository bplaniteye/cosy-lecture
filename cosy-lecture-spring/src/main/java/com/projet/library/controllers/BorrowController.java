package com.projet.library.controllers;

import com.projet.library.dao.request.CreateBorrowRequest;
import com.projet.library.entities.BorrowEntity;
import com.projet.library.entities.LibraryUserEntity;
import com.projet.library.repositories.BorrowRepository;
import com.projet.library.services.BorrowService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow/")
public class BorrowController {
    
    private final BorrowRepository borrowRepository;
    private final BorrowService borrowService;

    @GetMapping("getDetails")
    public LibraryUserEntity getCurrentUserDetails(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        if (authentication != null && authentication.isAuthenticated()) {
            // L'utilisateur est connecté, vous pouvez obtenir des informations sur l'utilisateur comme suit
            Object principal = authentication.getPrincipal();

            if (principal instanceof LibraryUserEntity) {
                LibraryUserEntity userDetails = (LibraryUserEntity) principal;
                // Accédez aux propriétés de votre utilisateur ici
                System.out.println("LibraryUserService : " + userDetails.getId());
                return userDetails;
                //String username = userDetails.getUsername();
                // ...
            }
            throw new IllegalStateException("Aucun utilisateur authentifié.");
            //System.out.println("Aucun utilisateur authentifié.");
        }
        throw new IllegalStateException("Aucun utilisateur authentifié.");
    }

    // Method to get all borrows
    @GetMapping("getAllBorrows")
    public ResponseEntity<Collection<BorrowEntity>> getAllBorrows() {
        return new ResponseEntity<>(borrowRepository.findAll(), HttpStatus.OK);
    }

    // Method to get borrows of a user
    @GetMapping("getBorrowsByUser/{id}")
    public ResponseEntity<Collection<BorrowEntity>> getBorrowsById(@PathVariable Integer id) {
        Collection<BorrowEntity> borrows = borrowRepository.findByUserId(id);
        return new ResponseEntity<>(borrows, HttpStatus.OK);
    }

    @PostMapping("createBorrow")
    public ResponseEntity<BorrowEntity> createBook(@RequestBody CreateBorrowRequest request, HttpServletRequest httpRequest) throws ServerException {
        System.out.println("Received book: " + request.getBook());
        BorrowEntity borrow = borrowService.createBorrow(request,httpRequest);
        System.out.println("Ma requête: " + request);
        if (borrow == null) {
            throw new ServerException("Impossible de créer le livre");
        } else {
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(borrow.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }
}

//System.out.println("Received user: " + request.getUser());
//LibraryUserEntity user = libraryUserService.getUser();
//System.out.println("Received user: " + user);


//     @PostMapping("createBorrow")
//     public ResponseEntity<BorrowEntity> createBorrow(@RequestParam Integer userId, @RequestParam Integer bookId) throws ServerException {
//     try {
//         // Récupérez l'utilisateur et le livre à partir de leurs identifiants respectifs
//         LibraryUserEntity user = libraryUserService.getUserById(userId);
//         BookEntity book = bookService.getBookById(bookId);

//         // Vérifiez si l'utilisateur et le livre existent
//         if (user == null || book == null) {
//             throw new ServerException("L'utilisateur ou le livre n'existe pas.");
//         }

//         // Créez un objet BorrowEntity avec les données nécessaires
//         BorrowEntity borrow = new BorrowEntity();
//         borrow.setStartDate(LocalDateTime.now()); // Date de début de l'emprunt
//         // Vous pouvez définir d'autres propriétés de l'emprunt ici si nécessaire

//         // Associez l'utilisateur et le livre à l'emprunt
//         borrow.setUser(user);
//         borrow.setBook(book);

//         // Appelez le service d'emprunt pour créer l'emprunt
//         BorrowEntity createdBorrow = borrowService.createBorrow(borrow);

//         // Retournez une réponse appropriée avec l'emprunt créé
//         URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdBorrow.getId()).toUri();
//         return ResponseEntity.created(url).body(createdBorrow);

//     } catch (Exception ex) {
//         throw new ServerException("Impossible de créer l'emprunt. " + ex.getMessage());
//     }
// }