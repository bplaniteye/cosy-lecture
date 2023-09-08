import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { URL_BACK_END_API } from '../environments/environment';
import { map } from 'rxjs';
import { LibraryUser } from '../models/LibraryUser';

@Injectable({
  providedIn: 'root',
})
export class LibraryUserService {
  constructor(private http: HttpClient) {}

  createLibraryUser(newUser: any) {
    return this.http.post(`${URL_BACK_END_API}/api/auth/signup`, newUser);
  }

  getAllLibraryUsers() {
    return this.http
      .get<LibraryUser[]>(`${URL_BACK_END_API}/api/libraryUser/getAllLibraryUsers`)
      .pipe(
        map((data) => {
          return data;
        })
      );
  }

  getUserById(id:number){
    return this.http.get<LibraryUser>(`${URL_BACK_END_API}/api/libraryUser/profile/{id}`);
  }

  getUserFirstName(id:number){
    return this.http.get<LibraryUser>(`${URL_BACK_END_API}/getFirstName/{id}`);
  }
}
