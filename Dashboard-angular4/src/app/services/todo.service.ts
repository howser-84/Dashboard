import { Injectable } from '@angular/core';
import {Todo} from '../classes/todo';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class TodoService {

  private todos: Todo[];
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.repopulateTodos();
  }

  public addTodo(name: string, description: string, deadline: Date): void{
    this.http.post('http://localhost:8080/add', {name: name, description: description, deadline: deadline})
      .subscribe(data => {this.repopulateTodos(); console.log(data); }, err => {console.error('Something went very wrong.' + err); });
  }

  public getTodos(): Todo[]{
    return this.todos;
  }

  public removeTodo(todo: Todo): void{
    this.http.post('http://localhost:8080/remove', {id: todo.id, name: todo.name, description: todo.description, deadline: todo.deadline})
      .subscribe(data => {this.repopulateTodos(); console.log(data); }, err => {console.error('Something went very wrong.' + err); });
  }

  public repopulateTodos(): void{
    this.headers = new HttpHeaders({'Content-type': 'application/json; charset=utf-8'});
    this.http.get<Todo[]>('http://localhost:8080/list', {headers: this.headers}).subscribe(data => {this.todos = data; console.log(data), err => {console.error('Something went very wrong.' + err); }; });
  }
}
