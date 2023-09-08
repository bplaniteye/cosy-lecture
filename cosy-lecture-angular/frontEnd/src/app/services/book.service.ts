import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { URL_BACK_END_API } from '../environments/environment';
import { Observable, map } from 'rxjs';
import { Author } from '../models/Author';
import { Category } from '../models/Category';
import { Editor } from '../models/Editor';
import { Book } from '../models/Book';


@Injectable({
  providedIn: 'root',
})
export class BookService {

  constructor(private http: HttpClient) {}

  getAllBooks() {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/getAllBooks`).pipe(
      map((data) => {
        return data;
      })
    );
  }

  createBook(
    title:string,
    quantity:number,
    summary:string,
    publicationYear:Date,
    version:string,
    author:Author,
    category:Category,
    editor:Editor
    ): Observable<any> {
    return this.http.post(
      `${URL_BACK_END_API}/api/admin/createBook`,
      {
        title,
        quantity,
        summary,
        publicationYear,
        version,
        author,
        category,
        editor
      },
    );
  }

  // Method to get details of a book
  getBookById(id: number){
    return this.http.get<Book>(`${URL_BACK_END_API}/api/book/getBookById/${id}`).pipe(
      map(data =>{
        return data
    })
    );
  }

  // Method to get the books of an author
  getBooksByAuthor(id : number) {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/getBooksByAuthor/${id}`).pipe(
      map(data =>{
        return data
      })
    );
  }

  // Method to get the books of a category
  getBooksByCategory(id : number) {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/getBooksByCategory/${id}`).pipe(
      map(data=>{
        return data
      })
    );
  }

  // Method to get the books of an editor
  getBooksByEditor(id : number) {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/getBookByEditor/${id}`).pipe(
      map(data=>{
        return data
      })
    );
  }

  getLatestBooks() {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/latestBooks`).pipe(
      map(data => {
        return data;
      })
    );
  }

  getAvailableBooks() {
    return this.http.get<Book[]>(`${URL_BACK_END_API}/api/book/getAvailableBooks`).pipe(
      map((data) => {
        return data;
      })
    );
  }
}
