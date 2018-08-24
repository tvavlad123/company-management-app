import {Department} from './department.model'


export class Team {
    public id: number;
    public name: string;
    public department: Department;

    constructor(id: number, name:string, department: Department){
        this.id=id;
        this.name=name;
        this.department=department;
    }
}