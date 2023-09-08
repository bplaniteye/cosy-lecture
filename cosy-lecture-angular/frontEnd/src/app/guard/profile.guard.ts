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
export class ProfileGuard {
  //message:string ='';
  constructor(public storageService: StorageService, public router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | UrlTree | boolean {
    if (this.storageService.isLoggedIn() !== true) {
      //this.message = "Vous ne pouvez accéder à cette page vous n'êtes pas connecté";
      window.alert('Access Denied, Login is Required to Access This Page!');
      this.router.navigate(['/login']);
      //this.showLoginErrorMessage();
    }
    return true;
  }

  // private showLoginErrorMessage() {
  //   const loginComponent = document.querySelector('app-login');
  //   if (loginComponent) {
  //     (loginComponent as any).showLoginErrorMessage = true;
  //   }
  // }

  //   goToConnexion() {
  //   this.message = "Votre inscription est réussie !";
  //   // Attendre pendant quelques secondes avant la redirection
  //   setTimeout(() => {
  //     this.router.navigate(['/login']);
  //   }, 3000); // Attendre 3 secondes (3000 millisecondes)
  // }
}
