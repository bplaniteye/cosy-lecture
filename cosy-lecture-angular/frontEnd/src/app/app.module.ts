import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination'; //TODO attention à installer la dépendance

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';
import { AdministratorComponent } from './administrator/administrator.component';

import { FormsModule } from '@angular/forms';
import { ErrorComponent } from './error/error.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoryComponent } from './category/category.component';
import { AuthorComponent } from './author/author.component';
import { RegisterComponent } from './register/register.component';
import { BorrowComponent } from './borrow/borrow.component';
import { LibraryUserComponent } from './library-user/library-user.component';
import { CreateAuthorComponent } from './author/create-author/create-author.component';
import { CreateCategoryComponent } from './category/create-category/create-category.component';
import { BooksByCategoryComponent } from './book/books-by-category/books-by-category.component';
import { BooksByAuthorComponent } from './book/books-by-author/books-by-author.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';

import { UpdatePasswordComponent } from './profile/update-password/update-password.component';
import { UpdateProfileComponent } from './profile/update-profile/update-profile.component';
import { CreateBookComponent } from './book/create-book/create-book.component';
import { AdminManageComponent } from './administrator/admin-manage/admin-manage.component';
import { EditorComponent } from './editor/editor.component';
import { BooksByEditorComponent } from './book/books-by-editor/books-by-editor.component';
import { CreateEditorComponent } from './editor/create-editor/create-editor.component';
import { CreateBorrowComponent } from './borrow/create-borrow/create-borrow.component';
import { AdminManageEditorComponent } from './administrator/admin-manage-editor/admin-manage-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookComponent,
    AdministratorComponent,
    CreateBookComponent,
    ErrorComponent,
    HeaderComponent,
    CategoryComponent,
    AuthorComponent,
    RegisterComponent,
    BorrowComponent,
    LibraryUserComponent,
    CreateAuthorComponent,
    CreateCategoryComponent,
    BooksByCategoryComponent,
    BooksByAuthorComponent,
    BookDetailsComponent,
    LoginComponent,
    ProfileComponent,
    UpdateProfileComponent,
    UpdatePasswordComponent,
    AdminManageComponent,
    EditorComponent,
    BooksByEditorComponent,
    CreateEditorComponent,
    CreateBorrowComponent,
    AdminManageEditorComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgxPaginationModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
