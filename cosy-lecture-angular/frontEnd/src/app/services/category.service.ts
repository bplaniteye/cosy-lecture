import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../models/Category';
import { URL_BACK_END_API } from '../environments/environment';
import { map } from 'rxjs';
//import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  getAllCategories() {
    return this.http
      .get<Category[]>(`${URL_BACK_END_API}/api/category/getAllCategories`)
      .pipe(
        map((data) => {
          return data;
        })
      );
  }

    createCategory(label:string, definition:string) {
        return this.http.post<Category>(`${URL_BACK_END_API}/api/admin/createCategory`,
            {
                label,
                definition
            }
        );
    }
}
