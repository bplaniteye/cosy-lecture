import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/models/Author';
import { Book } from 'src/app/models/Book';
import { Category } from 'src/app/models/Category';
import { Editor } from 'src/app/models/Editor';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { CategoryService } from 'src/app/services/category.service';
import { EditorService } from 'src/app/services/editor.service';

@Component({
  selector: 'app-admin-manage',
  templateUrl: './admin-manage.component.html',
  styleUrls: ['./admin-manage.component.css']
})
export class AdminManageComponent {

  constructor(
    private categoryService: CategoryService,
    private authorService: AuthorService,
    private bookService:BookService,
    private editorDervice:EditorService,
    private router:Router
  ) {}

  books: Book[] = [];
  categories: Category[] = [];
  authors: Author[] = [];
  editors: Editor[] = [];

  ngOnInit(): void {
    this.getBooksList();
    this.getCategoriesList();
    this.getAuthorsList();
  }

  getBooksList() {
    this.bookService.getAllBooks().subscribe({
      next: (dataBooks) => {
        console.log(dataBooks);
        this.books = dataBooks;
      },
    });
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
    this.editorDervice.getAllEditors().subscribe({
      next: (dataEditors) => {
        this.editors = dataEditors;
      },
    });
  }

  goToAddBook(){
    this.router.navigate(['/createBook']);
  }

}
