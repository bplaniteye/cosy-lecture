import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/Category';
import { BorrowService } from '../services/borrow.service';
import { Borrow } from '../models/Borrow';

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css'],
})
export class BorrowComponent {
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

  constructor(private borrowService: BorrowService) {}

  borrows: Borrow[] = [];

  ngOnInit(): void {
    this.getBorrowsList();
  }

  getBorrowsList() {
    this.borrowService.getAllBorrows().subscribe({
      next: (borrowsData) => {
        this.borrows = borrowsData;
      },
    });
  }
}
