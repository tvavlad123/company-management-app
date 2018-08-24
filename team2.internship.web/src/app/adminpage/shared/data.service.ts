import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';
import { Department } from '../../models/department.model';
import { User } from '../../models/user.model';
import { Team } from '../../models/team.model';
import { Position } from '../../models/position.model';
import { Authority } from '../../models/authority.model';
import { UserSkill } from '../../models/userskill.model';
import { UserLanguage } from '../../models/userlanguage.model';
import { Skill } from '../../models/skill.model';
import { Language } from '../../models/language.model';
import { TeamLead } from '../../models/teamlead.model';
import { ResetpasswordComponent } from '../../resetpassword/resetpassword.component';
import { ChangeRequest } from '../../resetpassword/changerequest.component';
import { Router } from '@angular/router';


import { SkillLevel } from '../../models/skilllevel.model';
import { TableEntry } from '../../models/tableentry.model';
import { LanguageLevel } from '../../models/languagelevel.model';
import 'rxjs/add/operator/toPromise';
import 'rxjs';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../../security/authentication.service';
import { VacationRequest } from "app/models/vacationrequest.model";
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { HomeOfficeDaysLeft } from "app/models/homeofficedaysleft.model";
import { VacationDaysLeft } from "app/models/vacationdaysleft.model";
import { HalfDay } from "app/models/halfday.model";
import { Status } from "app/models/status.model";

@Injectable()
export class DataService {
    private headers: Headers;

    constructor(private http: Http, private authenticationService: AuthenticationService,
      private router: Router) {
        this.headers = new Headers({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.authenticationService.getToken()
        });
    }

    verifyToken(){
        if(this.headers.get("Authorization").substring(7)!=this.authenticationService.getToken()){
            this.headers = new Headers;
            this.headers.append('Content-Type', 'application/json');
            this.headers.append('Authorization', 'Bearer ' + this.authenticationService.getToken());
        }
    }

    getUsers(): Promise<User[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallusers', { headers: this.headers }).toPromise()
            .then(response => response.json() as User[]).catch();
    }

    getUsers1(): Observable<User[]> {
        this.verifyToken();
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

    private handleErrorWithAlert(error: any): Promise<any> {
        console.log('An error occurred', error); // for demo purposes only
        alert("Server error");
        return Promise.reject(error.message || error);
    }

    getTeams(): Observable<Team[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallteams', { headers: this.headers }).map(res => res.json().teamEpoList).catch(this.handleError);
    }

    getPositions(): Observable<Position[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallpositions', { headers: this.headers }).map(res => res.json().epos).catch(this.handleError);
    }

    getAuthorities(): Observable<Authority[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallauthorities', { headers: this.headers }).map(res => res.json().authorityEpos).catch(this.handleError);

    }

    deleteTeam(team: Team): Observable<Team> {
        // console.log(JSON.stringify(team));
        this.verifyToken();
        return this.http.post('http://localhost:9123/deleteTeam', JSON.stringify(team), { headers: this.headers }).map(r => console.log(r, "merge")).catch(this.handleError);
    }
    getUserSkills(id: number): Observable<UserSkill[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/getskillsbyuser', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    getAllSkills(): Observable<UserSkill[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallskills', { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    private extractUserData(res: Response) {
        let body = res.json();

        return body.user || {};
    }

    create(user: User): Observable<User> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/addUser', JSON.stringify(user), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getUser(id: number): Observable<User> {
        this.verifyToken();
        return this.getUsers1()
            .map(users => users.find(user => user.id === id));

    }

    getUsersByName(name: string): Observable<User[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/getusersbyname', name, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    

    createTeam(team: Team): Observable<Team> {
        this.verifyToken();
        console.log(JSON.stringify(team));
        return this.http
            .post('http://localhost:9123/addTeam', JSON.stringify(team), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getSkills(): Observable<Skill[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallskills', { headers: this.headers }).map(res => res.json().skillEpoList).catch(this.handleError);
    }

    getLanguages(): Observable<Language[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getalllanguages', { headers: this.headers }).map(res => res.json().languageEpoList).catch(this.handleError);

    }

    deleteUser(id: number): Observable<User> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/deleteuser', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addDepartment(dept: Department): Observable<Department> {
        this.verifyToken();
        console.log(JSON.stringify(dept));
        return this.http
            .post('http://localhost:9123/adddepartment', JSON.stringify(dept), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteDepartment(dept: number): Observable<Department> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/deletedepartment', dept, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    deleteSkill(skill: Skill): Observable<Department> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/deleteskill', skill, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addSkill(skill: Skill): Observable<Department> {
        this.verifyToken();
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/addskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteLanguage(language: Language): Observable<Department> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/deletelanguage', language, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addLanguage(language: Language): Observable<Department> {
        this.verifyToken();
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/addlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingSkills(id: number): Observable<Skill[]> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getskillsremaining', id, { headers: this.headers })
            .map(res => res.json().skillEpoList)
            .catch(this.handleError);
    }

    addUserSkill(skill: UserSkill): Observable<UserSkill> {
        this.verifyToken();
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/adduserskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteUserSkill(skill: UserSkill): Observable<UserSkill> {
        this.verifyToken();
        console.log(JSON.stringify(skill));
        return this.http
            .post('http://localhost:9123/deleteuserskill', JSON.stringify(skill), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingLanguages(id: number): Observable<Language[]> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getlanguagesremaining', id, { headers: this.headers })
            .map(res => res.json().languageEpoList)
            .catch(this.handleError);
    }

    getUserLanguages(id: number): Observable<UserLanguage[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/getlanguagesbyuser', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);

    }

    addUserLanguage(language: UserLanguage): Observable<UserLanguage> {
        this.verifyToken();
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/adduserlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteUserLanguage(language: UserLanguage): Observable<UserLanguage> {
        this.verifyToken();
        console.log(JSON.stringify(language));
        return this.http
            .post('http://localhost:9123/deleteuserlanguage', JSON.stringify(language), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    updateUser(user: User): Observable<User> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/updateUser', JSON.stringify(user), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getRemainingTeams(id: number): Observable<Team[]> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getteamsremaining', id, { headers: this.headers })
            .map(res => res.json())
            .catch(this.handleError);
    }

    getTeamsLead(id: number): Observable<TeamLead[]> {
        this.verifyToken();
        console.log(id);
        return this.http.post('http://localhost:9123/getteamsbyuser', id, { headers: this.headers }).map(res => {
            console.log('getTeamsLead ', res.json());
            return res.json();
        }).catch(this.handleError);

    }

    getUsersByTeam(team: Team): Observable<User[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/getusersbyteam', team, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    addTeamLead(teamLead: TeamLead): Observable<TeamLead> {
        this.verifyToken();
        console.log(teamLead);
        return this.http.post('http://localhost:9123/addteamlead', teamLead, { headers: this.headers }).map(res => {
            console.log(res.json());
            res.json();
        }).catch(this.handleError);
    }

    deleteTeamLead(teamLead: TeamLead): Observable<TeamLead> {
        this.verifyToken();
        console.log(teamLead);
        return this.http.post('http://localhost:9123/deleteteamlead', teamLead, { headers: this.headers }).map(res => {
            console.log(res.json());
            return res.json();
        }).catch(this.handleError);
    }


    addUserToTeam(Id: number[]): Observable<User> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/addusertoteam', Id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getSkillLevels(): Observable<SkillLevel[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getallskilllevels', { headers: this.headers }).map(res => res.json().epoList).catch(this.handleError);


    }

    getLanguageLevels(): Observable<LanguageLevel[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getalllanguagelevels', { headers: this.headers }).map(res => res.json().epos).catch(this.handleError);


    }

    resetPassword(id: number): Observable<boolean> {
        this.verifyToken();
        console.log(id);
        return this.http
            .post('http://localhost:9123/resetpassword', id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    changePassword(changePassword: ChangeRequest): Observable<number> {
        this.verifyToken();
        console.log(changePassword);
        return this.http
            .post('http://localhost:9123/changepassword', changePassword, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    deleteMember(id: number): Observable<User> {
        this.verifyToken();
        return this.http.post('http://localhost:9123/removemember', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    getNonMembers(id: number): Observable<User[]> {
        this.verifyToken();
        console.log(id);
        return this.http.post('http://localhost:9123/getremainingusers', id, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    getUsersByNameFromTeam(name: string, team: Team): Observable<User[]> {
        this.verifyToken();
        let team2: Team = new Team(team.id, name, team.department)
        return this.http.post('http://localhost:9123/getusersbynamefromteam', team2, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    getUsersByNameNotFromTeam(name: string, team: Team): Observable<User[]> {
        this.verifyToken();
        let team2: Team = new Team(team.id, name, team.department)
        return this.http.post('http://localhost:9123/getusersbynamenotfromteam', team2, { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    }

    saveVacationRequest(vacation: VacationRequest): Observable<VacationRequest> {
        this.verifyToken();
        console.log(JSON.stringify(vacation));
        return this.http
            .post('http://localhost:9124/addVacationRequest', JSON.stringify(vacation), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    saveHomeOfficeRequest(homeOffice: HomeOfficeRequest): Observable<HomeOfficeRequest> {
        this.verifyToken();
        console.log(JSON.stringify(homeOffice));
        return this.http
            .post('http://localhost:9124/addHomeOfficeRequest', JSON.stringify(homeOffice), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleErrorWithAlert);
    }

    getAllHomeOfficeRequests(): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallhomerequests', { headers: this.headers }).map(res => res.json().homereqepolist).catch(this.handleError);
    }

    getAllVacationRequests(): Observable<VacationRequest[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallvacationrequests', { headers: this.headers }).map(res => res.json().vacationreqepolist).catch(this.handleError);
    }

    getAllStatusTypes(): Observable<Status[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallstatustypes', { headers: this.headers }).map(res => res.json().statusepolist).catch(this.handleError);
    }

    deleteHomeOfficeRequest(id: number): Observable<HomeOfficeRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/deletehomeofficerequest', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    deleteVacationRequest(id: number): Observable<VacationRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/deletevacationrequest', id, { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    updateHomeOfficeRequestStatus(homeOfficeRequest: HomeOfficeRequest): Observable<HomeOfficeRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/updatehomeofficerequest', JSON.stringify(homeOfficeRequest), { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    updateVacationRequestStatus(vacationRequest: VacationRequest): Observable<VacationRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/updatevacationrequest', JSON.stringify(vacationRequest), { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    getHomeOfficeRequestById(id: number): Observable<HomeOfficeRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/gethomeofficerequestbyid', id, { headers: this.headers }).map(res => res.json().homereqepolist).catch(this.handleError);
    }

    getVacationRequestById(id: number): Observable<VacationRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/getvacationrequestbyid', id, { headers: this.headers }).map(res => res.json().vacationreqepolist).catch(this.handleError);
    }

    getVacationRequestsByUser(user_id: number): Observable<VacationRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/getvacationrequestsbyuser', user_id, { headers: this.headers })
            .map(res => res.json().vacationreqepolist)
            .catch(this.handleError);
    }

    getHomeOfficeRequestsByUser(user_id: number): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/gethomeofficerequestsbyuser', user_id, { headers: this.headers })
            .map(res => res.json().homereqepolist)
            .catch(this.handleError);
    }

    addHomeOfficeRequest(homeOfficeRequest: HomeOfficeRequest): Observable<HomeOfficeRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/addHomeOfficeRequest', JSON.stringify(homeOfficeRequest), { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }

    addVacationRequest(vacationrequest: VacationRequest): Observable<VacationRequest> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/addVacationRequest', JSON.stringify(vacationrequest), { headers: this.headers }).map(this.extractData).catch(this.handleError);
    }


    findHomeOfficeRequestsUnresolvedForTeamCoord(team_id: number): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/gethomeofficerequestsunresolvedforteam', team_id, { headers: this.headers }).map(res => res.json().homereqepolist).catch(this.handleError);
    }

    findVacationRequestsUnresolvedForTeamCoord(team_id: number): Observable<VacationRequest[]> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/getvacationrequestsunresolvedforteam', team_id, { headers: this.headers }).map(res => res.json().vacationreqepolist).catch(this.handleError);
    }

    getAllHalfDayTypes(): Observable<HalfDay[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallhalfdaytypes', { headers: this.headers }).map(res => res.json().halfdayepolist).catch(this.handleError);
    }

    findVacationRequestsUnresolvedForUser(user_id: number): Observable<VacationRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/getvacationrequestsunresolvedforuser', user_id, { headers: this.headers })
            .map(res => res.json().vacationreqepolist)
            .catch(this.handleError);
    }

    findNumberOfDaysLeftAvailableToTake(user_id: number, start_date: Date, end_date: Date, half_day_id: number): Observable<number> {
        this.verifyToken();
        let homeOfficeDaysLeft: HomeOfficeDaysLeft = new HomeOfficeDaysLeft(user_id, start_date, end_date, half_day_id);
        console.log(JSON.stringify(homeOfficeDaysLeft));
        return this.http
            .post('http://localhost:9124/getnohomeofficedaysavailabletotake', JSON.stringify(homeOfficeDaysLeft), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    findNrOfAvailbleVacationDaysToTake(user_id: number, start_date: Date, end_date: Date): Observable<number> {
        this.verifyToken();
        let vacatioDayLeft: VacationDaysLeft = new VacationDaysLeft(user_id, start_date, end_date);
        console.log(JSON.stringify(vacatioDayLeft));
        return this.http
            .post('http://localhost:9124/getnrofvacationdaysavailabletotake', JSON.stringify(vacatioDayLeft), { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    findHomeOfficeRequestsAcceptedForUser(user_id: number): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/gethomeofficerequestsacceptedforuser', user_id, { headers: this.headers })
            .map(res => res.json().homereqepolist)
            .catch(this.handleError);
    }

    findHomeOfficeRequestsUnresolvedForUser(user_id: number): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/gethomeofficerequestsunresolvedforuser', user_id, { headers: this.headers })
            .map(res => res.json().homereqepolist)
            .catch(this.handleError);
    }

    findHomeOfficeRequestsDeclinedForUser(user_id: number): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/gethomeofficerequestsdeclinedforuser', user_id, { headers: this.headers })
            .map(res => res.json().homereqepolist)
            .catch(this.handleError);
    }

    findAllHomeOfficeRequestsUnresolved(): Observable<HomeOfficeRequest[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallhomeofficerequestsunresolved', { headers: this.headers }).map(res => res.json().homereqepolist).catch(this.handleError);
    }

    findAllVacationRequestsUnresolved(): Observable<VacationRequest[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9124/getallvacationrequestsunresolved', { headers: this.headers }).map(res => res.json().vacationreqepolist).catch(this.handleError);
    }

    // findNrOfHomeOfficeRequestsUnresolved(): Observable<number> {
    //     return this.http.get('http://localhost:9124/getnrofhomeofficerequestsunresolved', { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    // }

    // findNrOfVacationRequestsUnresolved(): Observable<number> {
    //     return this.http.get('http://localhost:9124/getnrofvacationrequestsunresolved', { headers: this.headers }).map(res => res.json()).catch(this.handleError);
    // }

    findNrOfVacationRequestsUnresolvedForTeamCoord(team_id: number): Observable<number> {
        this.verifyToken();
        console.log(JSON.stringify(team_id));
        return this.http
            .post('http://localhost:9124/getnrofvacationrequestsunresolvedforteam', team_id, { headers: this.headers })
            .map(res => res.json())
            .catch(this.handleError);
    }

    findNrOfHomeOfficeRequestsUnresolvedForTeamCoord(team_id: number): Observable<number> {
        this.verifyToken();
        console.log(JSON.stringify(team_id));
        return this.http
            .post('http://localhost:9124/getnrofhomeofficerequestsunresolvedforteam', team_id, { headers: this.headers })
            .map(res => res.json())
            .catch(this.handleError);
    }

    findVacationRequestsAcceptedForUser(user_id: number): Observable<VacationRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/getvacationrequestsacceptedforuser', user_id, { headers: this.headers })
            .map(res => res.json().vacationreqepolist)
            .catch(this.handleError);
    }

      findVacationRequestsDeclinedForUser(user_id: number): Observable<VacationRequest[]> {
        this.verifyToken();
        console.log(JSON.stringify(user_id));
        return this.http
            .post('http://localhost:9124/getvacationrequestsdeclinedforuser', user_id, { headers: this.headers })
            .map(res => res.json().vacationreqepolist)
            .catch(this.handleError);
    }

    // findNrOfHomeOfficeRequestsAcceptedForUserPerYear(user_id: number): Observable<number> {
    //     console.log(JSON.stringify(user_id));
    //     return this.http
    //         .post('http://localhost:9124/getnrofhomeofficerequestsacceptedforuserperyear', user_id, { headers: this.headers })
    //         .map(res => res.json())
    //         .catch(this.handleError);
    // }

    // findNrOfVacationRequestsAcceptedForUserPerYear(user_id: number): Observable<number> {
    //     console.log(JSON.stringify(user_id));
    //     return this.http
    //         .post('http://localhost:9124/getnrofvacationrequestsacceptedforuserperyear', user_id, { headers: this.headers })
    //         .map(res => res.json())
    //         .catch(this.handleError);
    // }

    getNrOfUsersFromATeam(team_id: number): Observable<number> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getnrofusersfromateam', team_id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getNameOfUsersFromATeam(team_id: number): Observable<any> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getnameofusersfromateam', team_id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    getNrOfTeamsFromATeamLead(user_id: number): Observable<number> {
        this.verifyToken();
        return this.http
            .post('http://localhost:9123/getnrofteamsfromateamlead', user_id, { headers: this.headers })
            .map(this.extractData)
            .catch(this.handleError);
    }

    findNrOfHomeOfficeRequestsAcceptedForUserPerYear(user_id: number): Observable<number>{
        this.verifyToken();
        return this.http
        .post('http://localhost:9124/getnrofhomeofficerequestsacceptedforuserperyear', user_id, { headers: this.headers})
        .map(this.extractData)
        .catch(this.handleError);
    }
    
    findNrOfVacationRequestsAcceptedForUserPerYear(user_id: number): Observable<number>{
        this.verifyToken();
        return this.http
        .post('http://localhost:9124/getnrofvacationrequestsacceptedforuserperyear', user_id, { headers: this.headers})
        .map(this.extractData)
        .catch(this.handleError);
    }

    getDepartments(): Observable<Department[]> {
        this.verifyToken();
        return this.http.get('http://localhost:9123/getalldepartments', { headers: this.headers }).map(res => res.json().departmentEpoList).catch(this.handleError);

    }

    findNrOfHomeOfficeRequestsUnresolved(user_id: number): Observable<number>{
        this.verifyToken();
        return this.http
        .get('http://localhost:9124/getnrofhomeofficerequestsunresolved', { headers: this.headers}).map(res => res.json().StatusEpo).catch(this.handleError);
    
    }

    findNrOfVacationRequestsUnresolved(user_id: number): Observable<number>{
        this.verifyToken();
        return this.http
        .get('http://localhost:9124/getnrofvacationrequestsunresolved', { headers: this.headers}).map(res => res.json().StatusEpo).catch(this.handleError);
    }

    findTeamsWithoutCoord(): Observable<Team[]>{
        this.verifyToken();
        return this.http.get('http://localhost:9123/getteamswithoutcoord', { headers: this.headers}).map(res => res.json()).catch(this.handleError);
    }

    getTableEntryForUser(user_id:number, month:number, year:number): Observable<TableEntry> {
        this.verifyToken();
        return this.http.post('http://localhost:9124/gettableforuser', {'id': user_id, 'month': month, 'year': year}, { headers: this.headers })
        .map(this.extractData)
        .catch(this.handleError);
    }

    getTeamById(team_id:number): Observable<Team> {
        this.verifyToken();
        return this.http
             .post('http://localhost:9123/getteambyid', team_id, { headers: this.headers })
             .map(res => res.json())
             .catch(this.handleError);
    }

    reroute(){
        
        this.router.navigate(["unauthorizederror"]);
    }

    // checkAdmin(): Observable<Boolean>{
    //     return this.http.get('http://localhost:9123/checkadmin', { headers: this.headers}).map(res => res.json()).catch(this.unauthorizedError);
    
    // }
}
