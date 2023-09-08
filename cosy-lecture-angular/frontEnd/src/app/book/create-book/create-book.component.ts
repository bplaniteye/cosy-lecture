import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { URL_BACK_END_API } from 'src/app/environments/environment';
import { Author } from 'src/app/models/Author';
import { Category } from 'src/app/models/Category';
import { Editor } from 'src/app/models/Editor';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { CategoryService } from 'src/app/services/category.service';
import { EditorService } from 'src/app/services/editor.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})

export class CreateBookComponent {
  titre: string = "Ajout d'un livre";
  message:string = "";

  constructor(
    private bookService:BookService,
    private router: Router,
    private categoryService: CategoryService,
    private authorService: AuthorService,
    private editorService:EditorService
    ) {}



    categories: Category[] = [];
    authors: Author[] = [];
    editors: Editor[] = [];

    ngOnInit(): void {
      //this.getBooksList();
      this.getCategoriesList();
      this.getAuthorsList();
      this.getEditorsList();
    }

    getCategoriesList() {
      this.categoryService.getAllCategories().subscribe({
        next: (dataCategories) => {
          this.categories = dataCategories;
        },
      });
    }

    getAuthorsList() {
      this.authorService.getAllAuthors().subscribe({
        next: (dataAuthors) => {
          this.authors = dataAuthors;
        },
      });
    }

    getEditorsList() {
      this.editorService.getAllEditors().subscribe({
        next: (dataEditors) => {
          this.editors = dataEditors;
        },
      });
    }

  form: any = {
    title: null,
    quantity: null,
    summary: null,
    createdAt:null,
    publicationYear:null,
    version:null,
    author:null,
    category:null,
    editor:null
  };
  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const {title, quantity, summary,publicationYear,version,author,category,editor} = this.form;

    this.bookService.createBook(title, quantity, summary,publicationYear,version,author,category,editor).subscribe({
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
    this.message = "Le livre a bien été ajouté !";
    // Attendre pendant quelques secondes avant la redirection
    setTimeout(() => {
      this.router.navigate(['/admin']);
    }, 3000); // Attendre 3 secondes (3000 millisecondes)
  }

}
