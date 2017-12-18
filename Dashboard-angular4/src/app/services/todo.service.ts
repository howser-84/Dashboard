import { Injectable } from '@angular/core';
import {Todo} from '../classes/todo';
import {Http, Response, Headers} from '@angular/http';

@Injectable()
export class TodoService {

  private todos: Todo[];
  private nextId: number;

  constructor(private http : Http) {
    /*this.todos = [
      new Todo(0, 'first task', 'dummy desc for first task'),
      new Todo(1, 'second task', 'dummy desc for second task'),
      new Todo(2, 'third task', 'dummy desc for third task')
    ]
    this.nextId = 3;*/
    this.nextId = 0;
  }

  public addTodo(name: string, description: string): void{
    console.log(this.todos);
    this.todos.push(new Todo(this.nextId, name, description));
    this.nextId++;
  }

  public getTodos(): Todo[]{
    this.http.get('http://localhost:8080/list').subscribe(data => {this.todos = data.json(); console.log(data);});
    return this.todos;
  }

  public removeTodo(id: number): void{
    this.todos = this.todos.filter((todo) => todo.id !== id);
  }
}
