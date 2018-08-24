import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Department } from '../../models/department.model';
import { User } from '../../models/userList.model';
import { Team } from '../../models/team.model';
import { Position } from '../../models/position.model';
import { Authority } from '../../models/authority.model';
import { UserSkill } from '../../models/userskillList.model';
import { UserLanguage } from '../../models/userlanguageList.model';
import { Skill } from '../../models/skill.model';
import { Language } from '../../models/language.model';
import { TeamLead } from '../../models/teamlead.model';
import { ResetpasswordComponent } from '../../resetpassword/resetpassword.component';
import { ChangeRequest } from '../../resetpassword/changerequest.component';

import { SkillLevel } from '../../models/skilllevel.model';
import { LanguageLevel } from '../../models/languagelevel.model';
import 'rxjs/add/operator/toPromise';
import 'rxjs';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../../security/authentication.service';

@Injectable()
export class DataServiceList {
    private headers: Headers;

    constructor(private http: Http, private authenticationService: AuthenticationService) {
        this.headers = new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.authenticationService.getToken()
        });
    }

    getUsers(): Observable<User[]> {
        return this.http.get('http://localhost:9123/getallusers', { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json() || {}
        console.log("DDD " + res + " BODY OF Role Data:", body)
        return body;
    }

    private handleError(error: any): Promise<any> {
        console.log('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getTeams(): Observable<Team[]> {
        return this.http.get('http://localhost:9123/getallteams', { headers: this.headers }).map(res => res.json().teamEpoList).catch(this.handleError);
    }

    getPositions(): Observable<Position[]> {
        return this.http.get('http://localhost:9123/getallpositions', { headers: this.headers }).map(res => res.json().epos).catch(this.handleError);
    }

    getAuthorities(): Observable<Authority[]> {
        return this.http.get('http://localhost:9123/getallauthorities', { headers: this.headers }).map(res => res.json().authorityEpos).catch(this.handleError);

    }

    deleteTeam(team: Team): Observable<Team> {
        // console.log(JSON.stringify(team));
        return this.http.post('http://localhost:9123/deleteTeam', JSON.stringify(team), { headers: this.headers }).map(r => console.log(r, "merge")).catch(this.handleError);
    }
    getUserSkills(id: number): Observable<UserSkill[]> {
        return this.http.post('http://localhost:9123/getskillsbyuser', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    getAllSkills(): Observable<UserSkill[]> {
        return this.http.get('http://localhost:9123/getallskills', { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    private extractUserData(res: Response) {
        let body = res.json();

        return body.user || {};
    }

    create(user: User): Observable<User> {
        return this.http
            .post('http://localhost:9123/addUser', JSON.stringify(user), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getUser(id: number): Observable<User> {
        return this.getUsers()
            .map(users => users.find(user => user.id === id));

    }

    getUsersByName(name: string): Observable<User[]> {
        return this.http.post('http://localhost:9123/getusersbyname', name, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    getDepartments(): Observable<Department[]> {
        return this.http.get('http://localhost:9123/getalldepartments', { headers: this.headers }).map(res => res.json().departmentEpoList).catch(this.handleError);

    }

    createTeam(team: Team): Observable<Team> {
        console.log(JSON.stringify(team));
        return this.http
            .post('http://localhost:9123/addTeam', JSON.stringify(team), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getSkills(): Observable<Skill[]> {
        return this.http.get('http://localhost:9123/getallskills', { headers: this.headers }).map(res => res.json().skillEpoList).catch(this.handleError);
    }

    getLanguages(): Observable<Language[]> {
        return this.http.get('http://localhost:9123/getalllanguages', { headers: this.headers }).map(res => res.json().languageEpoList).catch(this.handleError);

    }

    deleteUser(id: number): Observable<User> {
        return this.http.post('http://localhost:9123/deleteuser', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addDepartment(dept: Department): Observable<Department> {
        console.log(JSON.stringify(dept));
        return this.http
            .post('http://localhost:9123/adddepartment', JSON.stringify(dept), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteDepartment(dept: number): Observable<Department> {
        return this.http.post('http://localhost:9123/deletedepartment', dept, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    deleteSkill(skill: Skill): Observable<Department> {
        return this.http.post('http://localhost:9123/deleteskill', skill, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addSkill(skill: Skill): Observable<Department> {
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/addskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteLanguage(language: Language): Observable<Department> {
        return this.http.post('http://localhost:9123/deletelanguage', language, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addLanguage(language: Language): Observable<Department> {
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/addlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingSkills(id: number): Observable<Skill[]> {
        return this.http
            .post('http://localhost:9123/getskillsremaining', id, { headers: this.headers })
            .map(res => res.json().skillEpoList)
            .catch(this.handleError);
    }

    addUserSkill(skill: UserSkill): Observable<UserSkill> {
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/adduserskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteUserSkill(skill: UserSkill): Observable<UserSkill> {
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/deleteuserskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingLanguages(id: number): Observable<Language[]> {
        return this.http
            .post('http://localhost:9123/getlanguagesremaining', id, { headers: this.headers })
            .map(res => res.json().languageEpoList)
            .catch(this.handleError);
    }

    getUserLanguages(id: number): Observable<UserLanguage[]> {
        return this.http.post('http://localhost:9123/getlanguagesbyuser', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    addUserLanguage(language: UserLanguage): Observable<UserLanguage> {
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/adduserlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteUserLanguage(language: UserLanguage): Observable<UserLanguage> {
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/deleteuserlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    updateUser(user: User): Observable<User> {
        return this.http
            .post('http://localhost:9123/updateUser', JSON.stringify(user), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingTeams(id: number): Observable<Team[]> {
        return this.http
            .post('http://localhost:9123/getteamsremaining', id, { headers: this.headers })
            .map(res => res.json())
            .catch(this.handleError);
    }

    getTeamsLead(id: number): Observable<TeamLead[]> {
        console.log(id);
        return this.http.post('http://localhost:9123/getteamsbyuser', id, { headers: this.headers }).map(res => {
            console.log('getTeamsLead ', res.json());
            return res.json();
        }).catch(this.handleError);

    }

    getUsersByTeam(team: Team): Observable<User[]> {
        return this.http.post('http://localhost:9123/getusersbyteam', team, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    addTeamLead(teamLead: TeamLead): Observable<TeamLead> {
        console.log(teamLead);
        return this.http.post('http://localhost:9123/addteamlead', teamLead, { headers: this.headers }).map(res => {
            console.log(res.json());
            res.json();
        }).catch(this.handleError);
    }

    deleteTeamLead(teamLead: TeamLead): Observable<TeamLead> {
        console.log(teamLead);
        return this.http.post('http://localhost:9123/deleteteamlead', teamLead, { headers: this.headers }).map(res => {
            console.log(res.json());
            return res.json();
        }).catch(this.handleError);
    }


    addUserToTeam(Id: number[]): Observable<User> {
        return this.http.post('http://localhost:9123/addusertoteam', Id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getSkillLevels(): Observable<SkillLevel[]> {
        return this.http.get('http://localhost:9123/getallskilllevels', { headers: this.headers }).map(res => res.json().epoList).catch(this.handleError);


    }

    getLanguageLevels(): Observable<LanguageLevel[]> {
        return this.http.get('http://localhost:9123/getalllanguagelevels', { headers: this.headers }).map(res => res.json().epos).catch(this.handleError);


    }

    resetPassword(id: number): Observable<boolean> {
        console.log(id);
        return this.http
            .post('http://localhost:9123/resetpassword', id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    changePassword(changePassword: ChangeRequest): Observable<boolean> {
        console.log(changePassword);
        return this.http
            .post('http://localhost:9123/changepassword', changePassword, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteMember(id: number): Observable<User> {
        return this.http.post('http://localhost:9123/removemember', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    getNonMembers(id: number): Observable<User[]> {
        console.log(id);
        return this.http.post('http://localhost:9123/getremainingusers', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    getUsersByNameFromTeam(name: string, team: Team): Observable<User[]> {
        let team2: Team = new Team(team.id, name, team.department)
        return this.http.post('http://localhost:9123/getusersbynamefromteam', team2, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    getUsersByNameNotFromTeam(name: string, team: Team): Observable<User[]> {
        let team2: Team = new Team(team.id, name, team.department)
        return this.http.post('http://localhost:9123/getusersbynamenotfromteam', team2, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    findTeamsWithoutCoord(): Observable<Team[]>{
        return this.http.get('http://localhost:9123/getteamswithoutcoord', { headers: this.headers}).map(res => res.json()).catch(this.handleError);
    }
}
