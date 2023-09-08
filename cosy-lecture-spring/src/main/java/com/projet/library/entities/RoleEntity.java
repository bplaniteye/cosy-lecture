// package com.projet.library.entities;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.util.Collection;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class RoleEntity {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @Enumerated(EnumType.STRING)
//     @Column(length = 20)
//     private ERole name;

//     // @Column(length = 20)
//     // private String name;

//     //Relationships
//     @OneToMany(mappedBy = "role")
//     public Collection<LibraryUserEntity> userCollection;
// }
