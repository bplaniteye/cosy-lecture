import { Author } from './Author';
import { Category } from './Category';
import { Editor } from './Editor';

export class Book {
  public id!: number;
  public title!: string;
  public quantity!: number;
  public summary!: string;
  public available!: boolean;
  public createdAt!: Date;
  public publicationYear!: string;
  public version!: string;
  public author!: Author;
  public category!: Category;
  public editor!:Editor;
}
