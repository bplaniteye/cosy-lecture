import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Borrow } from '../models/Borrow';
import { URL_BACK_END_API } from '../environments/environment';
import { map } from 'rxjs';
import { LibraryUser } from '../models/LibraryUser';
import { Book } from '../models/Book';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root',
})
export class BorrowService {
  book!: Book;

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  getAllBorrows() {
    return this.http
      .get<Borrow[]>(`${URL_BACK_END_API}/api/borrow/getAllBorrows`)
      .pipe(
        map((data) => {
          return data;
        })
      );
  }

  createUserBorrow(libraryUser:LibraryUser,book: Book) {
    console.log(libraryUser);
    return this.http.post(`${URL_BACK_END_API}/api/borrow/createBorrow`, {
      libraryUser,
      book,
    });
  }

  adminCreateBorrow(
    startDate: Date,
    endDate: Date,
    returnDate: Date,
    isReturned: boolean,
    penalty: number,
    book: Book,
    user: LibraryUser
  ) {
    return this.http.post(`${URL_BACK_END_API}/api/admin/admin-create-borrow`, {
      startDate,
      endDate,
      returnDate,
      isReturned,
      penalty,
      book,
      user,
    });
  }
}
