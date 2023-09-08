import { Component } from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})

export class CreateCategoryComponent {
  title: string = "Ajout d'une catégorie";
  message:string = "";

  constructor(private categoryService: CategoryService,
  private router: Router) {}
  form: any = {
    label: null,
    definition: null
  };

  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const {label , definition} = this.form;

    this.categoryService.createCategory(label , definition).subscribe({
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
              "Une erreur s'est produite lors de la création de la catégorie.";
          console.log(this.errorMessage);
        }
        this.isAddFailed = true;
      },
    });
  }

  goToAdmin() {
    this.message = "La catégorie a bien été ajoutée !";
    // Attendre pendant quelques secondes avant la redirection
    setTimeout(() => {
      this.router.navigate(['/admin']);
    }, 3000); // Attendre 3 secondes (3000 millisecondes)
  }
}
