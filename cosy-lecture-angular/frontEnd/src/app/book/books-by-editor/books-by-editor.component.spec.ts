import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksByEditorComponent } from './books-by-editor.component';

describe('BooksByEditorComponent', () => {
  let component: BooksByEditorComponent;
  let fixture: ComponentFixture<BooksByEditorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BooksByEditorComponent]
    });
    fixture = TestBed.createComponent(BooksByEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
