import {Day} from './day.model';
export class TableEntry{

    public userId:number;
    public requestsImplementation:Day[];

    constructor(userId:number, requestsImplementation:Day[]){
        this.userId=userId;
        this.requestsImplementation=requestsImplementation;
    }
}