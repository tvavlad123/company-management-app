import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Params, Router } from "@angular/router";
import { DataService } from '../adminpage/shared/data.service';
import { User } from '../models/user.model';
import { Skill } from "app/models/skill.model";
import { UserSkill } from "app/models/userskill.model";
import { Department } from "app/models/department.model";
import { Team } from "app/models/team.model";
import { Authority } from "app/models/authority.model";
import { Position } from "app/models/position.model";
import { UserLanguage } from "app/models/userlanguage.model";
import { SkillLevel } from 'app/models/skilllevel.model';
import { Language } from 'app/models/language.model';
import { LanguageLevel } from 'app/models/languagelevel.model';
import { AuthenticationService } from '../security/authentication.service';
import { Location } from '@angular/common'; 
import {TeamLead} from 'app/models/teamlead.model';

import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService,
    private authenticationService: AuthenticationService,
    private location: Location) { }


  user: User = new User(0, "", "", "", "", new Authority(0, ""), "", new Position(0, ""), new Team(0, "", new Department(0, "")), null, null);
  userskills: UserSkill[];
  userlanguages: UserLanguage[];
  positions: Position[];
  selectedPosition: Position = null;
  skillLevels: SkillLevel[];
  languageLevels: LanguageLevel[];


  showUserSkills: boolean = false;
  showUserLanguages: boolean = false;

  username: string;
  searchUsers: User[];

  id: number;
  user_id_output:number=null;
  showCalendar:boolean=false;

  teamsLeader: TeamLead[];


  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.user_id_output=+params['id'];
      this.getUserById();
      this.getUserSkills();
      this.getUserLanguages();
      this.getSkillLevels();
      this.getLanguageLevels();
      this.dataService.getTeamsLead(this.id).subscribe(r => {
        this.teamsLeader = r;
        this.showCalendar=true;
      });
    });
  }

  getUserById(): void {
    this.dataService.getUser(this.id).subscribe(u => {
      this.user = u;
    });
  }

  getUserSkills(): void {
    this.dataService.getUserSkills(this.id).subscribe(uskills => this.userskills = uskills);
  }

  getUserLanguages(): void {
    this.dataService.getUserLanguages(this.id).subscribe(ulanguages => this.userlanguages = ulanguages);
  }


  getSkillLevels(): void {
    this.dataService.getSkillLevels().subscribe(skilllev => this.skillLevels = skilllev);
  }

  getLanguageLevels(): void {
    this.dataService.getLanguageLevels().subscribe(languagelev => this.languageLevels = languagelev);
  }

  onKeyUp(): void {
    this.searchMembers();
  }

  searchMembers(): void {
    console.log(this.username);
    if (this.username) {
      this.dataService.getUsersByName(this.username)
        .subscribe(searchUsernames => this.searchUsers = searchUsernames);
      console.log(this.searchUsers);
    }
    else
      this.searchUsers = null;
  }


  goToProfile(searchUser: User): void {
    this.router.navigate(['searchuser/' + searchUser.id]);
    this.searchUsers = null;
  }

  goBack():void{
   this.location.back();
  }

  



}
