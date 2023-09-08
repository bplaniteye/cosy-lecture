import { Injectable } from '@angular/core';
import { URL_BACK_END_API } from '../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LibraryUser } from '../models/LibraryUser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      `${URL_BACK_END_API}/api/auth/login`,
      {
        email,
        password
      },
      httpOptions
    );
  }


  register(email: string,
    password: string,
    passwordConfirm:string,
    role:string
    ): Observable<any> {
    return this.http.post(
      `${URL_BACK_END_API}/api/auth/register`,
      {
        email,
        password,
        role
      },
      httpOptions
    );
  }

  update(firstName:string, lastName:string, email: string): Observable<any>{
    return this.http.get(`${URL_BACK_END_API}/api/auth/logout`)
  }

  updatePassword(oldPassword:string, newPassword:string, confirmNewPassword: string): Observable<any>{
    return this.http.post(
      `${URL_BACK_END_API}/api/auth/register`,
      {
        oldPassword,
        newPassword,
        confirmNewPassword
      },
      httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.get(`${URL_BACK_END_API}/api/auth/logout`)
  }

  // logout(): Observable<any> {
  //   return this.http.post(AUTH_API + 'signout', { }, httpOptions);
  // }

}
