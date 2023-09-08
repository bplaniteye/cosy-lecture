import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent {
  title:string = 'Profil'

  constructor(
    private router: Router,
    private authService : AuthentificationService
  ) {}

  form: any = {
    firstName:null,
    lastName: null,
    email: null,
    password: null,
    passwordConfirm: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  ngOnInit(): void {}

  onSubmit(): void {
    const {firstName, lastName, email, password} = this.form;

    // this.authService.update(firstName, lastName,email, password).subscribe({
    //   next: (data) => {
    //     console.log(data);
    //     this.isSuccessful = true;
    //     this.isSignUpFailed = false;
    //   },
    //   error: (err) => {
    //     if (err.error && err.error.message) {
    //       this.errorMessage = err.error.message;
    //     } else {
    //       this.errorMessage =
    //         "Une erreur s'est produite lors de l'inscription.";
    //       console.log(this.errorMessage);
    //     }
    //     this.isSignUpFailed = true;
    //   },
    // });
    // this.goToConnexion();
  }

  goToConnexion() {
    this.router.navigate(['/login']);
  }


}
