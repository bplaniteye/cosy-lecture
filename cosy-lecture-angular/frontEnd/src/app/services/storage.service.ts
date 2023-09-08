import { Injectable } from '@angular/core';
import { Role } from '../enums/Role';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private USER_KEY = 'auth-user';

  public email: any;
  public password: any;
  //public role!:Role;
  public role:string[]=[];


  constructor() { }

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(this.USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(this.USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }

  removeUser() {
    sessionStorage.removeItem(this.USER_KEY);
    this.email = null;
    this.password = null;
  }

  getUserId(): number | null {
    const userId = localStorage.getItem(this.USER_KEY);
    if (userId) {
      console.log(userId);
      return parseInt(userId, 10); // Parse the stored string as an integer
    }
    return null; // Return null if user ID is not found in storage
  }
}
