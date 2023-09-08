import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/Category';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent {

  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(
    private categoryService: CategoryService

  ) { }

  categories: Category[] = [];
  searchTerm: string = '';
  searchResults: Category[] = [];
  showFilterMenu: boolean = false;

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    forkJoin({
      categories: this.categoryService.getAllCategories(),
    }).subscribe(result => {
      this.categories = result.categories;

      this.categorySearch();
    });
  }

  categorySearch() {
    if (this.searchTerm.trim() === '') {
      this.searchResults = this.categories;
    } else {
      console.log(this.searchTerm);
      this.searchResults = this.categories.filter(categories =>
        categories.label.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
    this.pages = 1;
  }

  sortByLabel() {
    this.searchResults.sort((a, b) => a.label.localeCompare(b.label));
  }

  toggleFilterMenu() {
    this.showFilterMenu = !this.showFilterMenu;
  }
}
