import { Component } from '@angular/core';
import { Book } from '../../models/Book';
import { BookService } from '../../services/book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'src/app/models/Author';
import { Category } from 'src/app/models/Category';
import { BorrowService } from 'src/app/services/borrow.service';
import { StorageService } from 'src/app/services/storage.service';
import { LibraryUser } from 'src/app/models/LibraryUser';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css'],
})
export class BookDetailsComponent {
  message:string = "";
  book: Book = new Book();
  author: Author = new Author();
  category: Category = new Category();

  ngOnInit(): void {
    // this.currentUser = this.storageService.getUser().id;
    // console.log(this.currentUser);
    const paramId = this.route.snapshot.params['id'];
    if (paramId) {
      const id = +paramId;
      this.booksById(id);
    }
  }

  constructor(
    private bookService: BookService,
    private borrowService:BorrowService,
    private storageService :StorageService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  booksById(id: number) {
    this.bookService.getBookById(id).subscribe({
      next: (data) => {
        this.book = data;
      },
    });
  }

  isSuccessful = false;
  isAddFailed = false;
  errorMessage = '';

  borrowBook(): void {
    const user = this.storageService.getUser(); // Récupérez l'objet LibraryUser de l'utilisateur connecté
    //console.log(user);
    // const bookId = this.route.snapshot.params['id'];
    const book = this.book;


    this.borrowService.createUserBorrow(user,book).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isAddFailed = false;
        this.goToBook();
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.errorMessage = err.error.message;
        } else {
          this.errorMessage =
            "Une erreur s'est produite lors de l'emprunt'.";
          console.log(this.errorMessage);
        }
        this.isAddFailed = true;
      },
    });
  }

  goToBook() {
    this.message = "Merci d'avoir emprunter ce livre, n'oubliez pas de le récupérer !";
    // Attendre pendant quelques secondes avant la redirection
    setTimeout(() => {
      this.router.navigate(['/book']);
    }, 3000); // Attendre 3 secondes (3000 millisecondes)
  }
}
