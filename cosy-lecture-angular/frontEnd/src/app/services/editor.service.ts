import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {URL_BACK_END_API} from "../environments/environment";
import {Editor} from "../models/Editor";


@Injectable({
  providedIn: 'root'
})
export class EditorService {

  constructor(private http: HttpClient) {  }

  createEditor(name:string) {
    return this.http.post<Editor>(`${URL_BACK_END_API}/api/admin/createEditor`,
        {
            name
        }
    );
  }

  getAllEditors(){
    return this.http.get<Editor[]>(`${URL_BACK_END_API}/api/editor/getAllEditors`).pipe(
      map((data) => {
        return data;
      }
    ));
  }

  deleteEditor(id: number){
    return this.http.delete<Editor>('${URL_BACK_END_API}/api/admin/delete-editor/${id}');
}
}
