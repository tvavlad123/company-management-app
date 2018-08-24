export class Day{

    public dayInMonth: number;
    public month: number;
    public year: number;
    public type: string;

    constructor(dayInMonth:number,month:number,year:number,type:string){
        this.dayInMonth=dayInMonth;
        this.month=month;
        this.year=year;
        this.type=type;
    }

}