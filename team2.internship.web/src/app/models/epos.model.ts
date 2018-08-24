import { UserLanguage } from "app/models/userlanguageList.model";

export class LanguageEpos {
    public epos: UserLanguage[];

    constructor(epos: UserLanguage[]){
        this.epos = epos;
    }
}