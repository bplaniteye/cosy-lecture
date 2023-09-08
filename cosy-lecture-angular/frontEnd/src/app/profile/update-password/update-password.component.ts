import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { LibraryUserService } from 'src/app/services/library-user.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent {

  constructor(
    private router: Router,
    private libraryUserService: LibraryUserService,
    private authService : AuthentificationService
  ) {}


  form: any = {
    oldPassword:null,
    newPassword: null,
    confirmNewPassword: null,
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  ngOnInit(): void {}

  onSubmit(): void {
    const {oldPassword, newPassword, confirmNewPassword} = this.form;

    this.authService.updatePassword(oldPassword, newPassword,confirmNewPassword).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
        } else {
          this.errorMessage =
            "Une erreur s'est produite lors de l'inscription.";
          console.log(this.errorMessage);
        }
        this.isSignUpFailed = true;
      },
    });
    this.goToProfil();
  }

  goToProfil() {
    this.router.navigate(['/profile']);
  }

}
