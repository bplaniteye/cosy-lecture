import { Component, ElementRef } from '@angular/core';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  title:string = "Profil";
  currentUser: any;
  currentUserRole:any;
  showFirstNameForm = false;
  firstNameValue: string = '';

  constructor(private storageService: StorageService, private el:ElementRef) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    console.log(this.currentUser);
    this.currentUserRole = this.storageService.getUser().role;
    console.log(this.currentUserRole);
  }

  toggleFirstNameForm() {
    this.showFirstNameForm = !this.showFirstNameForm;
  }

  handleInputChange(event: any) {
    this.firstNameValue = event.target.value;
  }

  toogleHandleProfil(){
    const btnChangeFirstName = this.el.nativeElement.querySelector("#btnChangeFirstName");
    const btnValidChangeFirstName = this.el.nativeElement.querySelector("#btnValidChangeFirstName");
    const divFirstName = this.el.nativeElement.querySelector("#firstName");
    const divchangeFirstName = this.el.nativeElement.querySelector("#changeFirstName");
    divFirstName.classList.add("d-none");
    divchangeFirstName.classList.remove("d-none");
  }

}
