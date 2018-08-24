import { User } from './userList.model';
import { Language } from './language.model';
import { LanguageLevel } from './languagelevel.model';

export class UserLanguage {

    public id: number;
    public user: User;
    public level: LanguageLevel;
    public language: Language;

    constructor(id:number,user:User,level:LanguageLevel,language:Language){
        this.id=id;
        this.user=user;
        this.language=language;
        this.level=level;
    }
}