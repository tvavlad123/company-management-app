export class Role{
    public id: number;
    public name: string;
    public type: string;

    constructor(id: number, name:string, type: string){
        this.id=id;
        this.name=name;
        this.type=type;
    }
}
