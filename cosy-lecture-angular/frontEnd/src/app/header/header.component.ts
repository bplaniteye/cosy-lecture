import { Component } from '@angular/core';
import { StorageService } from '../services/storage.service';
import { AuthentificationService } from '../services/authentification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  private role: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  firstName?: string;

  constructor(private storageService: StorageService,
    private authService: AuthentificationService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      if(user){
        //console.log(user);
        this.showAdminBoard = user.role === 'ROLE_ADMIN';
      }
      this.isLoggedIn = this.storageService.isLoggedIn();
      this.firstName = user.firstName;
    }
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
    this.goToLogin();
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
