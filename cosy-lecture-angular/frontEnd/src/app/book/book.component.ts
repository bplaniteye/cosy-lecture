import { Component } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../models/Book';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/Category';
import { AuthorService } from '../services/author.service';
import { Author } from '../models/Author';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css'],
})
export class BookComponent {
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(
    private bookService: BookService,
    private categoryService: CategoryService,
    private authorService: AuthorService
  ) { }

  books: Book[] = [];
  categories: Category[] = [];
  authors: Author[] = [];
  searchResults: Book[] = [];
  searchTerm: string = '';
  showFilterMenu: boolean = false;


  ngOnInit(): void {
    this.pages = 1;
    this.getBooksList();
    this.getCategoriesList();
    this.getAuthorsList();
    console.log(this.getBooksList());
    this.loadData();
  }

  loadData() {
    forkJoin({
      books: this.bookService.getAllBooks(),
      categories: this.categoryService.getAllCategories(),
      authors: this.authorService.getAllAuthors(),
    }).subscribe(result => {
      this.books = result.books;
      this.categories = result.categories;
      this.authors = result.authors;

      this.bookSearch();
    });
  }

  getBooksList() {
    this.bookService.getAllBooks().subscribe({
      next: (dataBooks) => {
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

  bookSearch() {
    if (this.searchTerm.trim() === '') {
      this.searchResults = this.books;
    } else {
      this.searchResults = this.books.filter(book =>
        book.title.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
    this.pages = 1;
  }

  sortByTitle() {
    this.searchResults.sort((a, b) => a.title.localeCompare(b.title));
  }

  sortByDate() {
    this.searchResults.sort((a, b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime());
  }

  toggleFilterMenu() {
    this.showFilterMenu = !this.showFilterMenu;
  }
}
