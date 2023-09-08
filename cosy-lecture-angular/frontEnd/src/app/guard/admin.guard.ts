import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../services/storage.service';
@Injectable({
  providedIn: 'root',
})
export class AdminGuard {
  //message:string ='';
  currentUserRole: any;
  constructor(public storageService: StorageService, public router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | UrlTree | boolean {
    this.currentUserRole = this.storageService.getUser().role;
    if (this.storageService.isLoggedIn() && this.currentUserRole == 'ROLE_ADMIN') {
      // L'utilisateur est connecté et a le rôle ROLE_ADMIN
      return true; // Autoriser l'accès
    } else {
      // Soit l'utilisateur n'est pas connecté, soit il n'a pas le rôle ROLE_ADMIN
      window.alert('Access Denied, Login with Admin Privileges is Required to Access This Page!');
      this.router.navigate(['/login']);
      return false;
      //this.showLoginErrorMessage();
    }
  }
}
