import { Component } from '@angular/core';
import { AuthorService } from '../services/author.service';
import { Author } from '../models/Author';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css'],
})
export class AuthorComponent {

  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(
    private authorService: AuthorService,

  ) { }

  authors: Author[] = [];
  searchTerm: string = '';
  searchResults: Author[] = [];
  showFilterMenu: boolean = false;


  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    forkJoin({
      authors: this.authorService.getAllAuthors(),
    }).subscribe(result => {
      this.authors = result.authors;

      this.authorSearch();
    });
  }

  authorSearch() {
    if (this.searchTerm.trim() === '') {
      this.searchResults = this.authors;
    } else {
      console.log(this.searchTerm);
      this.searchResults = this.authors.filter(author =>
        author.firstname.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        author.lastname.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
    this.pages = 1;
  }

  sortByFirstName() {
    this.searchResults.sort((a, b) => a.firstname.localeCompare(b.firstname));
  }

  sortByLastName() {
    this.searchResults.sort((a, b) => a.lastname.localeCompare(b.lastname));
  }

  sortByDate() {
    this.searchResults.sort((a, b) => new Date(a.birthday).getTime() - new Date(b.birthday).getTime());
  }

  toggleFilterMenu() {
    this.showFilterMenu = !this.showFilterMenu;
  }
}
