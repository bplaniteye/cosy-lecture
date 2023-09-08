import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminManageEditorComponent } from './admin-manage-editor.component';

describe('AdminManageEditorComponent', () => {
  let component: AdminManageEditorComponent;
  let fixture: ComponentFixture<AdminManageEditorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminManageEditorComponent]
    });
    fixture = TestBed.createComponent(AdminManageEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
