import { Component, OnInit } from '@angular/core';
import {TodoService} from "../../services/todo.service";

@Component({
  selector: 'app-todo-input',
  templateUrl: './todo-input.component.html',
  styleUrls: ['./todo-input.component.css']
})
export class TodoInputComponent implements OnInit {

  private todoName: string;
  private todoDescription: string;
  private todoDeadline: Date;

  constructor(private todoService: TodoService) {
    this.todoName = '';
    this.todoDescription = '';
    this.todoDeadline = new Date();
  }

  ngOnInit() {
  }

  private addTodo(): void{
    this.todoService.addTodo(this.todoName, this.todoDescription, this.todoDeadline);
    this.todoName = '';
    this.todoDescription = '';
    this.todoDeadline = new Date();
  }

}
