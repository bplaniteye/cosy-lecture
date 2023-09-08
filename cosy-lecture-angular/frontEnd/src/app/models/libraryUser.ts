export class LibraryUser {
  public id!: number;
  public firstName!:string;
  public lastName!:string;
  public password!:string;
  public email!:string;
  public birthday!:Date;
  public phoneNumber!:string;
  public address!:string;
  public passwordConfirm: string | undefined;
  public role: string | undefined;
}
