package com.projet.library.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.projet.library.entities.LibraryUserEntity;
import com.projet.library.exception.UserNotFoundException;
import com.projet.library.repositories.LibraryUserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LibraryUserService {
    
    private final LibraryUserRepository libraryUserRepository;

    public LibraryUserEntity getUserById(Integer id) {
        Optional<LibraryUserEntity> user = libraryUserRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }
}


    // public LibraryUserEntity getUser() {
    //     // Obtenez l'objet Authentication du contexte de sécurité
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     System.out.println(authentication);

    //     if (authentication != null && authentication.isAuthenticated()) {
    //         // L'utilisateur est connecté, vous pouvez obtenir des informations sur l'utilisateur comme suit
    //         Object principal = authentication.getPrincipal();

    //         if (principal instanceof LibraryUserEntity) {
    //             LibraryUserEntity userDetails = (LibraryUserEntity) principal;
    //             // Accédez aux propriétés de votre utilisateur ici
    //             System.out.println("LibraryUserService : " + userDetails.getId());
    //             return userDetails;
    //             //String username = userDetails.getUsername();
    //             // ...
    //         }
    //         throw new IllegalStateException("Aucun utilisateur authentifié.");
    //         //System.out.println("Aucun utilisateur authentifié.");
    //     }
    //     throw new IllegalStateException("Aucun utilisateur authentifié.");
    // }
//       public LibraryUserEntity getUser() {
//       LibraryUserEntity user = (LibraryUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//       System.out.println("Mon user " + user);
//       return user;
//       // if (user instanceof UserDetails) {
//       //     // Integer userId = user.getId(); // Obtenez l'ID de l'utilisateur
//       //     // System.out.println("ID de l'utilisateur connecté : " + userId);
//       //     return (LibraryUserEntity) user;
//       // }
//       // System.out.println("Aucun utilisateur authentifié.");
//       // return null;
      
//   }

  //   public LibraryUserEntity getUser() {
  //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  //     if (authentication != null && authentication.getPrincipal() instanceof LibraryUserEntity) {
  //         LibraryUserEntity user = (LibraryUserEntity) authentication.getPrincipal();
  //         Integer userId = user.getId(); // Obtenez l'ID de l'utilisateur
  //         System.out.println("ID de l'utilisateur connecté : " + userId);
  //         return user;
  //     }
  //     System.out.println("Aucun utilisateur authentifié.");
  //     return null;
  // }

    // public LibraryUserEntity getUser(){
    //     //On récupére l'objet Authentication du contexte de sécurité
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
    //       System.out.println("je suis la.");
    //         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //         Integer userId = ((LibraryUserEntity) userDetails).getId(); // Obtenez l'ID de l'utilisateur
    //         System.out.println("ID de l'utilisateur connecté : " + userId);
    //         //Obtenez l'objet LibraryUserEntity correspondant à l'ID de l'utilisateur depuis votre source de données (par exemple, la base de données).
    //         LibraryUserEntity userEntity = libraryUserRepository.findById(userId)
    //             .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
    //             System.out.println(userEntity);
    //     }
    //     System.out.println("Aucun utilisateur authentifié.");
    //     return null;
    // }
