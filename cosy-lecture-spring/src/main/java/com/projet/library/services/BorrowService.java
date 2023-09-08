package com.projet.library.services;

import com.projet.library.dao.request.CreateBorrowRequest;
import com.projet.library.entities.BorrowEntity;
import com.projet.library.entities.LibraryUserEntity;
import com.projet.library.repositories.BorrowRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Transactional
@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final LibraryUserService libraryUserService;
    private final JwtService jwtService;

    public String extractTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Supprimer "Bearer " pour obtenir le token
        }
        return null;
    }

    public BorrowEntity createBorrow (@RequestBody CreateBorrowRequest request,HttpServletRequest httpRequest) {
        // Obtenez le token JWT à partir de la demande ou d'où vous le stockez
        String jwtToken = extractTokenFromHeader(httpRequest);
        //String jwtToken = "votre-token-jwt"; // Vous devrez obtenir le token JWT ici

         // Vérifiez si le token JWT est null ou vide
        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new IllegalArgumentException("Token JWT manquant ou vide.");
        }

        // Utilisez le JwtService pour extraire l'ID de l'utilisateur
        int userId = jwtService.extractUserId(jwtToken);

        if (userId != 0) {
            //Maintenant, vous avez l'ID de l'utilisateur, vous pouvez l'utiliser pour créer l'emprunt
            LibraryUserEntity user = libraryUserService.getUserById(userId);

            BorrowEntity newBorrow = new BorrowEntity();
            newBorrow.setUser(user);
            newBorrow.setBook(request.getBook());
            System.out.println(request.getBook());
            newBorrow.setPenalty(0.0f);

            return borrowRepository.save(newBorrow);
        } else {
            throw new IllegalStateException("Aucun utilisateur authentifié.");
        }
    }

    public BorrowEntity adminCreateBorrow (CreateBorrowRequest request) {
        BorrowEntity borrow = new BorrowEntity();
        borrow.setStartDate(request.getStartDate());
        borrow.setEndDate(request.getStartDate().plusDays(30));
        borrow.setPenalty(0.0f);
        // book.setQuantity(request.getBook().getQuantity() - 1);
        // if (request.getBook().getQuantity() == 0) {
        //     request.getBook().setAvailable(false);
        // }
        //bookRepository.save(request.getBook());
        return borrowRepository.save(borrow);
        // return "Borrow created";
    }
}
    // public BorrowEntity createBorrow () {
    //     borrow.setStartDate(LocalDateTime.now());
    //     borrow.setEndDate(LocalDateTime.now().plusDays(30));
    //     borrow.setIsReturned(false);
    //     borrow.setUser(user);
    //     borrow.setBook(book);
    //     borrow.setPenalty(0.0f);
    //     book.setQuantity(book.getQuantity() - 1);
    //     if (book.getQuantity() == 0) {
    //         book.setAvailable(false);
    //     }
    //     bookRepository.save(book);
    //     return borrowRepository.save(borrow);
    //     // return "Borrow created";
    // }

   /* public float calculatePenalty(BorrowEntity borrow) {
        if (borrow.getReturnDate().isAfter(borrow.getEndDate())) {
            borrow.setBorrowDelay(delay);
            borrow.setPenalty((float) (delay * 0.5));
            borrowRepository.save(borrow);
        }
        return borrow.getPenalty();
    }

    public String returnBook() {
        borrow.setReturnDate(LocalDateTime.now());
        borrow.setIsReturned(true);
        borrow.setPenalty(calculatePenalty(borrow));
        book.setAvailable(true);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        borrowRepository.save(borrow);
        return "Book returned";
    }*/