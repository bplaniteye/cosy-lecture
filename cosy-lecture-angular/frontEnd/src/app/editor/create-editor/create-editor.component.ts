import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {EditorService} from "../../services/editor.service";

@Component({
  selector: 'app-create-editor',
  templateUrl: './create-editor.component.html',
  styleUrls: ['./create-editor.component.css']
})
export class CreateEditorComponent {
title: string = "Ajout d'un éditeur";
    message:string = "";

  constructor(private editorService: EditorService,
              private router: Router) {}
  form: any = {
    name: null
  };

  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const {name} = this.form;

    this.editorService.createEditor(name).subscribe({
      next: (data) => {
        this.isSuccessful = true;
        this.isAddFailed = false;
        this.goToAdmin();
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
        } else {
          this.errorMessage =
              "Une erreur s'est produite lors de la création de l'éditeur.";
          console.log(this.errorMessage);
        }
        this.isAddFailed = true;
      },
    });
  }

  goToAdmin() {
    this.message = "L'éditeur a bien été ajouté !";
    // Attendre pendant quelques secondes avant la redirection
    setTimeout(() => {
      this.router.navigate(['/admin']);
    }, 3000);
  }

}
