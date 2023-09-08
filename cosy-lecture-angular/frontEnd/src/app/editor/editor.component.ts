import {Component, OnInit} from '@angular/core';
import {EditorService} from "../services/editor.service";
import {Editor} from "../models/Editor";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit{
  title = 'EDITEURS';
  pages: number = 1;
  dataset: any[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];

constructor(private editorService : EditorService) { }
  ngOnInit() {
    this.getEditorsList();
  }
  editors: Editor[] = [];

  getEditorsList() {
    this.editorService.getAllEditors().subscribe({
      next: (editorData) => {
        this.editors = editorData;
      },
    });
  }
}
