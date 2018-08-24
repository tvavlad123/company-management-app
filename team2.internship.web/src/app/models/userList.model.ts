import { Authority } from './authority.model';
import { Position } from './position.model';
import { Team } from './team.model';
import { UserSkill } from './userskillList.model';
import { UserLanguage } from './userlanguageList.model';
import { SkillEpos } from "app/models/epolist.model";
import { LanguageEpos } from "app/models/epos.model";

export class User {
    public id: number;
    public firstName: string;
    public lastName: string;
    public userName: string;
    public password: string;
    public authority: Authority;
    public photoLocation: string;
    public position: Position;
    public team: Team;
    public userSkills: SkillEpos;
    public userLanguages: LanguageEpos;


    constructor(id: number, firstName: string, lastName: string, userName: string, password: string, authority: Authority, photoLocation: string, position: Position, team: Team, userskill: SkillEpos, userlanguage: LanguageEpos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.authority = authority;
        this.photoLocation = photoLocation;
        this.position = position;
        this.team = team;
        this.userSkills = userskill;
        this.userLanguages = userlanguage;
    }

    public updateUser(other: User): void {
        this.id = other.id;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.userName = other.userName;
        this.password = other.password;
        this.authority = other.authority;
        this.photoLocation = other.photoLocation;
        this.position = other.position;
        this.team = other.team;
        this.userSkills = other.userSkills;
        this.userLanguages = other.userLanguages;
    }
}