package com.projet.library.entities;

// import java.util.HashSet;
// import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projet.library.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @Table(name = "libraryuser", uniqueConstraints = {
//         @UniqueConstraint(columnNames = "email")
// })
@Table(name = "libraryuser")
public class LibraryUserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    // @Column(nullable = false,length = 100)
    // private String username = firstName+"."+lastName;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;
    // TODO : Change the type and set nullable to false
    // TODO : Uncomment
    //@Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    //@Column(length = 50)
    private String phoneNumber;


    private String address;
    // TODO : Set nullable to false
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    public Collection<BorrowEntity> borrowCollection;

    @Transient
    private String username;

    // private Set<RoleEntity> roles = new HashSet<>();

    // public Set<RoleEntity> getRoles() {
    // 	return roles;
    // }

    // public void setRoles(Set<RoleEntity> roles) {
    // 	this.roles = roles;
    // }

    // //Relationships
    // @ManyToOne
    // private RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
        //throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
}
