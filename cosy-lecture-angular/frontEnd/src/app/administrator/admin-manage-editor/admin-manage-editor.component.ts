import { Component , OnInit , OnChanges } from '@angular/core';
import {EditorService} from "../../services/editor.service";
import {Editor} from "../../models/Editor";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-admin-manage-editor',
  templateUrl: './admin-manage-editor.component.html',
  styleUrls: ['./admin-manage-editor.component.css']
})
export class AdminManageEditorComponent {
title:string = "Liste des éditeurs";
  editors: Editor[] = [];
  constructor(private editorService: EditorService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getEditorsList();
    const paramId = this.route.snapshot.params['id'];
    if (paramId) {
      const id = +paramId;
      this.deleteEditor(id);
    }
  }

  getEditorsList() {
    this.editorService.getAllEditors().subscribe({
      next: (data) => {
        this.editors = data;
      },
    });
  }

  deleteEditor(id: number): void {
    this.editorService.deleteEditor(id).subscribe(
      response => {
        alert(response);
       this.ngOnInit()
      },
      error => {
        alert("There was an error during the deletion!");
      }

    );
  }
}
