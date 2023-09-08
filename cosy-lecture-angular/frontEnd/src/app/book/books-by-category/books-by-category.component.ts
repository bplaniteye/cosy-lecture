import { Component } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/Book';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-books-by-category',
  templateUrl: './books-by-category.component.html',
  styleUrls: ['./books-by-category.component.css'],
})
export class BooksByCategoryComponent {
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(private route: ActivatedRoute, private bookService: BookService) {}

  books: Book[] = []; // Modifiez le type ici

  ngOnInit(): void {
    this.pages = 1;
    const paramId = this.route.snapshot.params['id'];
    if (paramId) {
      const id = +paramId;
      this.booksByCategory(id);
    }
  }

  booksByCategory(id:number) {
    this.bookService.getBooksByCategory(id).subscribe({
      next: (dataBooksByCategory) => {
        this.books = dataBooksByCategory;
      },
    });
  }
}
