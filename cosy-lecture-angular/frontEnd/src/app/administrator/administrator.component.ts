import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from '../services/book.service';
import { CategoryService } from '../services/category.service';
import { AuthorService } from '../services/author.service';
import { Category } from '../models/Category';
import { Author } from '../models/Author';
import { Book } from '../models/Book';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css'],
})
export class AdministratorComponent {
  title:string = "AdminBoard";
  constructor(
    private router:Router
  ) {}

  books: Book[] = [];
  categories: Category[] = [];
  authors: Author[] = [];

  ngOnInit(): void {

  }

  goToAdminManageBooks(){
    this.router.navigate(['/admin-manage']);
  }

  goToCreateBook(){
    this.router.navigate(['/create-book']);
  }

  goToAdminManageAuthors(){
    this.router.navigate(['/admin-manage-authors']);
  }

  goToCreateAuthor(){
    this.router.navigate(['/create-author']);
  }

  goToAdminManageCategories(){
    this.router.navigate(['/admin-manage-categories']);
  }

  goToCreateCategory(){
    this.router.navigate(['/create-category']);
  }

  goToAdminManageEditors(){
    this.router.navigate(['/admin-manage-editor']);
  }

  goToCreateEditor(){
    this.router.navigate(['/create-editor']);
  }

  goToAdminManageUsers(){
    this.router.navigate(['/admin-manage-users']);
  }

  goToCreateUser(){
    this.router.navigate(['/create-user']);
  }

  goToAdminManageBorrows(){
    this.router.navigate(['/borrow']);
  }

  goToAdminCreateBorrow(){
    this.router.navigate(['/admin-create-borrow']);
  }
}
