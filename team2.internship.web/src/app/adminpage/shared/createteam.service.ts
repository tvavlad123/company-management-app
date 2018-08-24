import {Injectable} from '@angular/core';
import {Team} from '../../models/team.model';
import {Headers, Http} from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TeamService{
    constructor(private http: Http){}
     getTeams(): Promise<Team[]>{
        return this.http.get('http://localhost:9123/getallteams').toPromise()
        .then(response => response.json() as Team[]).catch();
    }
}