
import { Book } from './Book';
import { LibraryUser } from './LibraryUser';

export class Borrow {
  public id!: number;
  public startDate!: Date;
  public endDate!: Date;
  public returnDate!: Date;
  public isReturned!: boolean;
  public penalty!: number;
  public user!: LibraryUser;
  public book!: Book;
}
