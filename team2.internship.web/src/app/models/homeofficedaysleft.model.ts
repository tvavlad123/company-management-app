export class HomeOfficeDaysLeft {

    
    public start_date: Date;
    public end_date: Date;
    public half_day_id: number;
    public user_id: number;

    constructor(user_id: number, start_date: Date, end_date: Date, half_day_id: number) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.half_day_id = half_day_id;
        this.user_id = user_id;
    }

}