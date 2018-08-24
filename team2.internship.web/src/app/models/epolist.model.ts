import { UserSkill } from "app/models/userskillList.model";

export class SkillEpos {
    public epoList: UserSkill[];

    constructor(epoList:UserSkill[]){
        this.epoList = epoList;
    }
}