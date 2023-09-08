import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {BorrowService} from "../../services/borrow.service";
import {LibraryUser} from "../../models/LibraryUser";
import {Book} from "../../models/Book";
import {BookService} from "../../services/book.service";
import {LibraryUserService} from "../../services/library-user.service";

@Component({
  selector: 'app-create-borrow',
  templateUrl: './create-borrow.component.html',
  styleUrls: ['./create-borrow.component.css']
})
export class CreateBorrowComponent {
  title: string = "Emprunter un livre";
  message:string = "";
  users: LibraryUser[] = [];
  books: Book[] = [];

  constructor(
    private borrowService:BorrowService,
    private userService:LibraryUserService,
    private bookService:BookService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAvailableBooksList();
    this.getUsersList();
  }

  form: any = {
  startDate: null,
   endDate: null,
   returnDate: null,
   isReturned: false,
   penalty: 0,
   user: null,
   book: null
  };
  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const {startDate, endDate, returnDate, isReturned, penalty, book, user} = this.form;
    this.borrowService.adminCreateBorrow(startDate, endDate, returnDate, isReturned, penalty, book, user).subscribe({
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
            "Une erreur s'est produite lors de l'emprunt du livre.";
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

  getAvailableBooksList() {
    this.bookService.getAvailableBooks().subscribe({
      next: (data) => {
        this.books = data;
      },
    });
  }

  getUsersList() {
    this.userService.getAllLibraryUsers().subscribe({
      next: (data) => {
        this.users = data;
        console.log(data);
      },
    });
  }

}
