import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';
import { AdministratorComponent } from './administrator/administrator.component';
import { ErrorComponent } from './error/error.component';
import { AuthorComponent } from './author/author.component';
import { CategoryComponent } from './category/category.component';
import { CreateAuthorComponent } from './author/create-author/create-author.component';
import { BorrowComponent } from "./borrow/borrow.component";
import { BooksByCategoryComponent } from "./book/books-by-category/books-by-category.component";
import {BooksByAuthorComponent} from "./book/books-by-author/books-by-author.component";
import {BookDetailsComponent} from "./book/book-details/book-details.component";
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdateProfileComponent } from './profile/update-profile/update-profile.component';
import { UpdatePasswordComponent } from './profile/update-password/update-password.component';
import {EditorComponent} from "./editor/editor.component";
import {BooksByEditorComponent} from "./book/books-by-editor/books-by-editor.component";
import { ProfileGuard } from './guard/profile.guard';
import {CreateCategoryComponent} from "./category/create-category/create-category.component";
import { CreateBookComponent } from './book/create-book/create-book.component';
import { AdminManageComponent } from './administrator/admin-manage/admin-manage.component';
import {CreateEditorComponent} from "./editor/create-editor/create-editor.component";
import {CreateBorrowComponent} from "./borrow/create-borrow/create-borrow.component";
import {AdminManageEditorComponent} from "./administrator/admin-manage-editor/admin-manage-editor.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  { path: 'profile', component: ProfileComponent, canActivate: [ProfileGuard] },
  {path:'updateProfile',component:UpdateProfileComponent},
  {path:'updatePassword',component:UpdatePasswordComponent},
  { path: 'home', component: HomeComponent },
  { path: 'book', component: BookComponent },
  {path:'create-book',component: CreateBookComponent},
  {path: 'book-details/:id' , component: BookDetailsComponent},
  {path: 'books-by-author/:id' , component: BooksByAuthorComponent},
  { path: 'books-by-category/:id', component: BooksByCategoryComponent },
  { path: 'books-by-editor/:id', component: BooksByEditorComponent },
  { path: 'author', component: AuthorComponent },
  { path: 'addAuthor', component: CreateAuthorComponent },
  { path: 'category', component: CategoryComponent },
  {path:'create-category', component: CreateCategoryComponent},
  { path: 'admin', component: AdministratorComponent },
  {path:'admin-manage', component:AdminManageComponent},
  { path: 'borrow', component: BorrowComponent },
  {path: 'editor', component: EditorComponent},
  {path: 'admin-create-borrow', component: CreateBorrowComponent},
  {path: 'create-category', component: CreateCategoryComponent},
  {path: 'create-editor', component: CreateEditorComponent},

  { path: 'admin-manage-editor', component: AdminManageEditorComponent },
  { path: 'admin-delete-editor/:id', component: AdminManageEditorComponent },

  { path: '**', component: ErrorComponent },
  { path: 'editor', component: EditorComponent },
  { path: '**', component: ErrorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
