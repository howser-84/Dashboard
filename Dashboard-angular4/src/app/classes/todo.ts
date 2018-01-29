export class Todo {
  id: number;
  name: string;
  description: string;
  deadline: Date;

  constructor(id: number, name: string, description: string, deadline: Date){
    this.id = id;
    this.name = name;
    this.description = description;
    this.deadline = deadline;
  }
}
