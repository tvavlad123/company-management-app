
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { User } from "app/models/user.model";

export class HORequest {

    public request: HomeOfficeRequest;
    public user: User;

    constructor(req: HomeOfficeRequest, us: User) {
        this.request=req;
        this.user=us;
    }
}