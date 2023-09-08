import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../models/Book';

@Component({
  selector: 'app-books-by-author',
  templateUrl: './books-by-author.component.html',
  styleUrls: ['./books-by-author.component.css'],
})
export class BooksByAuthorComponent implements OnInit {
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute
  ) {}

  books: Book[] = [];

  ngOnInit(): void {
    this.pages = 1;
    const paramId = this.route.snapshot.params['id'];
    if (paramId) {
      const id = +paramId;
      this.booksByAuthor(id);
    }
  }

  booksByAuthor(id: number) {
    this.bookService.getBooksByAuthor(id).subscribe({
      next: (data) => {
        this.books = data;
      },
    });
  }
}
