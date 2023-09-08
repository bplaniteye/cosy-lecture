import { Component, OnInit } from '@angular/core';
import { LibraryUser } from '../models/LibraryUser';
import { LibraryUserService } from '../services/library-user.service';

@Component({
  selector: 'app-library-user',
  templateUrl: './library-user.component.html',
  styleUrls: ['./library-user.component.css'],
})
export class LibraryUserComponent implements OnInit {
  users: LibraryUser[] = [];

  constructor(private libraryUserService: LibraryUserService) {}

  ngOnInit(): void {
    this.getUsersList();
  }

  getUsersList() {
    this.libraryUserService.getAllLibraryUsers().subscribe({
      next: (libraryUserData) => {
        this.users = libraryUserData;
      },
    });
  }
}
