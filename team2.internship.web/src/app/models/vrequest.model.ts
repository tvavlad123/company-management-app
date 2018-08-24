import { VacationRequest } from "app/models/vacationrequest.model";
import { User } from "app/models/user.model";

export class VRequest {

    public request: VacationRequest;
    public user: User;

    constructor(req: VacationRequest, us: User) {
        this.request=req;
        this.user=us;
    }
}