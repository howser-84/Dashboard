import { Injectable } from '@angular/core';
import {Todo} from '../classes/todo';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class TodoService {

  private todos: Todo[];
  private headers: HttpHeaders;
  private nextId: number;

  constructor(private http : HttpClient) {
    this.repopulateTodos();
    this.nextId = 0;
  }

  public addTodo(name: string, description: string): void{
    console.log(this.todos);
    //this.todos.push(new Todo(this.nextId, name, description));
    this.headers = new HttpHeaders({'Content-type': 'application/json; charset=utf-8'});
    this.http.post<Todo>("http://localhost:8080/add",{id: this.nextId, name: name, description: description});
    this.nextId++;
    this.repopulateTodos();
  }

  public getTodos(): Todo[]{
    return this.todos;
  }

  public removeTodo(id: number): void{
    this.todos = this.todos.filter((todo) => todo.id !== id);
  }

  public repopulateTodos(): void{
    this.headers = new HttpHeaders({'Content-type': 'application/json; charset=utf-8'});
    this.http.get<Todo[]>("http://localhost:8080/list",{headers:this.headers}).subscribe(data => {this.todos = data});
  }
}
