import { User } from './user.model';
import { Skill } from './skill.model';
import {SkillLevel} from './skilllevel.model';

export class UserSkill {

    public id: number;
    public user: User;
    public skill: Skill;
    public level: SkillLevel;

    constructor(id:number,user:User,skill:Skill,level:SkillLevel){
        this.id=id;
        this.user=user;
        this.skill=skill;
        this.level=level;
    }
}