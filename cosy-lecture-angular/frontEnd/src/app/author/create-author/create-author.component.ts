import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorService } from 'src/app/services/author.service';

@Component({
  selector: 'app-create-author',
  templateUrl: './create-author.component.html',
  styleUrls: ['./create-author.component.css']
})
export class CreateAuthorComponent {
  title: string = "Ajout d'un auteur";
  message:string = "";

  constructor(
    private authorService:AuthorService,
    private router: Router
    ) {}

  form: any = {
    firstname: null,
    lastname: null,
    bio: null,
    birthday:null,
    deathday:null
  };
  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const {firstname, lastname, bio, birthday,deathday} = this.form;

    this.authorService.createAuthor(firstname, lastname, bio, birthday,deathday).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isAddFailed = false;
        this.goToAdmin();
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
        } else {
          this.errorMessage =
            "Une erreur s'est produite lors de l'inscription.";
          console.log(this.errorMessage);
        }
        this.isAddFailed = true;
      },
    });
  }

  goToAdmin() {
    this.message = "L'auteur a bien été ajouté !";
    // Attendre pendant quelques secondes avant la redirection
    setTimeout(() => {
      this.router.navigate(['/admin']);
    }, 3000); // Attendre 3 secondes (3000 millisecondes)
  }

}
