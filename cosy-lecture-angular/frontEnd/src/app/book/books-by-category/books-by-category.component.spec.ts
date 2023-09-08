import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksByCategoryComponent } from './books-by-category.component';

describe('BooksByCategoryComponent', () => {
  let component: BooksByCategoryComponent;
  let fixture: ComponentFixture<BooksByCategoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BooksByCategoryComponent]
    });
    fixture = TestBed.createComponent(BooksByCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
