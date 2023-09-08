import { TestBed } from '@angular/core/testing';
import { ProfileGuard } from './profile.guard';


describe('ProfileGuard', () => {
  let service: ProfileGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileGuard);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
