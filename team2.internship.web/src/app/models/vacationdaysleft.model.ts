export class VacationDaysLeft {
    
    public start_date: Date;
    public end_date: Date;
    public user_id: number;

    constructor(user_id: number, start_date: Date, end_date: Date) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_id = user_id;
    }

}