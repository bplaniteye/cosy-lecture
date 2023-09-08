import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { URL_BACK_END_API } from '../environments/environment';
import { map } from 'rxjs';
import { Author } from '../models/Author';

@Injectable({
  providedIn: 'root',
})
export class AuthorService {
  constructor(private http: HttpClient) {}

  getAllAuthors() {
    return this.http.get<Author[]>(`${URL_BACK_END_API}/api/author/getAllAuthors`).pipe(
      map((data) => {
        return data;
      })
    );
  }

  createAuthor(firstname:string,lastname:string,bio:string,birthday:Date,deathday:Date) {
    return this.http.post(`${URL_BACK_END_API}/api/admin/createAuthor`,
    {
      firstname,
      lastname,
      bio,
      birthday,
      deathday
    }
    );
  }
}
