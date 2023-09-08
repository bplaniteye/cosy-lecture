import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { AuthorService } from '../services/author.service';
import { Category } from '../models/Category';
import { Author } from '../models/Author';
import { BookService } from '../services/book.service';
import { Book } from '../models/Book';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(
    private bookService: BookService,
    private categoryService: CategoryService,
    private authorService: AuthorService
  ) { }

  latestBooks: Book[] = [];
  categories: Category[] = [];
  authors: Author[] = [];

  ngOnInit(): void {
    //this.getBooksList();
    this.getCategoriesList();
    this.getAuthorsList();
    this.getLatestBooks();
  }

  // getBooksList() {
  //   this.bookService.getAllBooks().subscribe({
  //     next: (dataBooks) => {
  //       this.books = dataBooks;
  //     },
  //   });
  // }

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
  
  getLatestBooks() {
    this.bookService.getLatestBooks().subscribe(data => {
      this.latestBooks = data;
    });
  }
}
