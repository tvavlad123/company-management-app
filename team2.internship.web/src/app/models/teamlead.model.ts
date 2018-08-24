import { Team } from './team.model';
import { User } from './user.model';

export class TeamLead {

    public id: number;
    public user: User;
    public team: Team;

    constructor(id:number, user:User, team:Team){
        this.id=id;
        this.user=user;
        this.team=team;
    }
}