import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../../services/book.service";
import {Book} from "../../models/Book";


@Component({
  selector: 'app-books-by-editor',
  templateUrl: './books-by-editor.component.html',
  styleUrls: ['./books-by-editor.component.css']
})
export class BooksByEditorComponent implements OnInit {
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(private route: ActivatedRoute, private bookService: BookService) {}

  books: Book[] = []; // Modifiez le type ici

  ngOnInit(): void {
    this.pages = 1;
    const paramId = this.route.snapshot.params['id'];
    if (paramId) {
      const id = +paramId;
      this.booksByEditor(id);
    }
  }

  booksByEditor(id:number) {
    this.bookService.getBooksByEditor(id).subscribe({
      next: (data) => {
        this.books = data;
      },
    });
  }
}
