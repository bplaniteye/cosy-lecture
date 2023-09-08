import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../services/authentification.service';
import { StorageService } from '../services/storage.service';
import { Router } from '@angular/router';
import { LibraryUser } from '../models/LibraryUser';
import { LibraryUserService } from '../services/library-user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  title: string = "Connexion";
  firstName: any = '';
  user: LibraryUser = new LibraryUser();
  messageError: string ='Erreur survenue lors de la récuperation des données utilisateur';
  //message:string = "";
  //showLoginErrorMessage: boolean = false;
  form: any = {
    email: null,
    password: null,
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  //roles: string[] = [];
  currentUser!: any;
  role!: string;

  constructor(
    private authService: AuthentificationService,
    private storageService: StorageService,
    private userService: LibraryUserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
        this.isLoggedIn = true;
        this.role = this.storageService.getUser().roles;
        this.currentUser = this.storageService.getUser();
        console.log(this.currentUser);
        //console.log(this.getFirstName())
      }
  }

  onSubmit(): void {
    const { email, password } = this.form;
    this.role = 'ROLE_USER';
    this.authService.login(email, password).subscribe({
      next: (data) => {
        console.log(data);
        this.storageService.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.role = this.storageService.getUser().roles;
        this.reloadPage();
        //this.goToHome();
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
        } else {
          console.log(err);
          this.errorMessage = 'An error occurred during login.';
        }
        this.isLoginFailed = true;
      },
    });
  }

  reloadPage(): void {
    window.location.reload();
  }

  goToHome() {
    this.router.navigate(['/home']);
  }
}
