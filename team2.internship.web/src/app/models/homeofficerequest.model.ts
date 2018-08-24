import { Status } from "app/models/status.model";
import { HalfDay } from "app/models/halfday.model";

export class HomeOfficeRequest {

    public id: number;
    public user_id: number;
    public start_date: Date;
    public end_date: Date;
    public half_day: HalfDay;
    public status_id: Status;

    constructor(id: number, user_id: number, start_date: Date, end_date: Date, half_day: HalfDay, status_id: Status) {
        this.id = id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.half_day = half_day;
        this.status_id = status_id;
    }
    
}