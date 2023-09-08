import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryUserComponent } from './library-user.component';

describe('LibraryUserComponent', () => {
  let component: LibraryUserComponent;
  let fixture: ComponentFixture<LibraryUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LibraryUserComponent]
    });
    fixture = TestBed.createComponent(LibraryUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
