import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { AdminGuard } from './admin.guard';

describe('ProfileGuard', () => {
  let service: AdminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminGuard);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
