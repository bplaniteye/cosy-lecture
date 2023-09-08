import { Component } from '@angular/core';
//import { LibraryUser } from '../models/LibraryUser';
import { LibraryUserService } from '../services/library-user.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthentificationService } from '../services/authentification.service';
import { Role } from '../enums/Role';
//import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  title: string = "S'inscrire";
  message:string = "";

  constructor(
    private router: Router,
    //private libraryUserService: LibraryUserService,
    private authService : AuthentificationService
  ) {}

  form: any = {
    email: null,
    password: null,
    passwordConfirm: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  passwordsMatch = true;

  ngOnInit(): void {}

  onSubmit(): void {
    const {email, password, passwordConfirm} = this.form;
    const role: Role = Role.ROLE_USER;

    if (password !== passwordConfirm) {
      this.passwordsMatch = false;
      this.errorMessage = "Les mots de passe ne correspondent pas";
      console.log(this.passwordsMatch);
      console.log(this.errorMessage);
      return;
    }

    //Le bloc .subscribe() permet de gérer la réponse de la requête
    this.authService.register(email, password, passwordConfirm, role).subscribe({
      //next: (data) => {...}:Cette partie gère la réponse réussie de la requête.
      //Le paramètre data contient les données renvoyées par le backend en cas de réussite.
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.goToConnexion();
      },
      //error: (err) => {...}: Cette partie gère les erreurs de la requête.
      //Si quelque chose se passe mal dans la requête, cette partie du code est exécutée.
      //Le paramètre err contient les détails de l'erreur renvoyée par le backend.
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
          console.log(this.errorMessage);
        } else {
          this.errorMessage = "Une erreur s'est produite lors de l'inscription.";
          console.log(this.errorMessage);
        }
        this.isSignUpFailed = true;
      },
    });
  }

  goToConnexion() {
    this.message = "Votre inscription est réussie !";
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 3000); // Attendre 3 secondes (3000 millisecondes)
  }
}
