import { Status } from "app/models/status.model";

export class VacationRequest {

    public id: number;
    public user_id: number;
    public start_date: Date;
    public end_date: Date;
    public comments: String;
    public picture: String;
    public status_id: Status;

    constructor(id: number, user_id: number, start_date: Date, end_date: Date, comments: String, picture: String, status_id: Status) {
        this.id = id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.comments = comments;
        this.picture = picture;
        this.status_id = status_id;
    }
}