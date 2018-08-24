import { Component, OnInit, Input, Output } from '@angular/core';
import { User } from '../../models/userList.model';
import { DataServiceList } from '../../adminpage/shared/dataList.service';
import { CompareService } from '../../adminpage/shared/compare.service';
import { ActivatedRoute, Params } from "@angular/router";
import { Authority } from '../../models/authority.model';
import { Position } from '../../models/position.model';
import { Team } from '../../models/team.model';
import { TeamLead } from '../../models/teamlead.model';
import { Department } from '../../models/department.model';
import { Skill } from '../../models/skill.model';
import { Language } from '../../models/language.model';
import { SkillLevel } from '../../models/skilllevel.model';
import { LanguageLevel } from '../../models/languagelevel.model';
import { UserLanguage } from '../../models/userlanguageList.model';
import { UserSkill } from '../../models/userskillList.model';
import { ListusersComponent } from '../../listusers/listusers.component';
import { EventEmitter } from '@angular/core';
import { SkillEpos } from "app/models/epolist.model";
import { LanguageEpos } from "app/models/epos.model";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
})
export class UpdateUserComponent implements OnInit {
  @Input() selectedUserId: number = 0;
  @Input() userToUpdate: User;
  @Input() skills: Skill[];
  @Input() languages: Language[];
  
  //initialUserState: User = new User(null, null, null, null, null, null, null, null, null, null, null);

  currentSkills: UserSkill[] = new Array<UserSkill>();
  availableSkills: Skill[] = new Array<Skill>();
  selectedCurrentSkill: UserSkill;
  selectedAvailableSkill: Skill = null;
  @Input() skillLevels: SkillLevel[];
  skillLevelToAdd: SkillLevel = null;

  currentLanguages: UserLanguage[] = new Array<UserLanguage>();
  availableLanguages: Language[] = new Array<Language>();
  selectedCurrentLanguage: UserLanguage;
  selectedAvailableLanguage: Language = null;
  @Input() languageLevels: LanguageLevel[];
  languageLevelToAdd: LanguageLevel = null;

  @Input() hideBool: boolean;
  @Output() hideBoolChange = new EventEmitter<boolean>();

  @Output() closeLine: EventEmitter<string> = new EventEmitter();

  teamsToAdd: TeamLead[] = new Array<TeamLead>();
  allTeams: Team[] = new Array<Team>();

  constructor(private userService: DataServiceList,
    private route: ActivatedRoute,
    private compare: CompareService) { }


  ngOnInit(): void {
    this.selectedUserId = this.userToUpdate.id;
    this.currentSkills = this.userToUpdate.userSkills.epoList;
    this.currentLanguages = this.userToUpdate.userLanguages.epos;
    this.getAvailableLanguages();
    this.getAvailableSkills();

    if (this.userToUpdate.authority.authorityType === "Team Leader") {
      this.getTeams(this.userToUpdate.id);
    }
    // console.log(this.userToUpdate.authority);
  }

  getTeams(id: number) {
    this.userService.getRemainingTeams(this.userToUpdate.id).subscribe(r => this.allTeams = r);
    this.userService.getTeamsLead(this.userToUpdate.id).subscribe(r => {
      this.teamsToAdd = r;
    });
  }

  updateUser(): void {
    for (let i = 0; i < this.userToUpdate.userLanguages.epos.length; i++) {
      this.userToUpdate.userLanguages.epos[i].user = null;
    }
    for (let i = 0; i < this.userToUpdate.userSkills.epoList.length; i++) {
      this.userToUpdate.userSkills.epoList[i].user = null;
    }
    this.userService.updateUser(this.userToUpdate).subscribe(r => {
      this.closeLine.emit('complete');
    });
  }

  selectedTeamLead: TeamLead;
  selectedTeamNew: Team=null;

  onSelectTeam(team: Team): void {
    this.selectedTeamNew = team;
  }

  onSelectTeamCurrent(team: TeamLead): void {
    this.selectedTeamLead = team;
  }

  deleteTeamLead(): void {
    let teamLead = this.selectedTeamLead;
    this.userService.deleteTeamLead(teamLead).subscribe(r => this.getTeams(this.userToUpdate.id));
  }

  addTeamLead(): void {
    let teamLead = new TeamLead(0, this.userToUpdate, this.selectedTeamNew);
    this.userService.addTeamLead(teamLead).subscribe(r => this.getTeams(this.userToUpdate.id));
  }

  onSelectCurrentSkill(userSkill: UserSkill): void {
    this.selectedCurrentSkill = userSkill;
  }

  onSelectCurrentLanguage(userLanguage: UserLanguage): void {
    this.selectedCurrentLanguage = userLanguage;
  }

  onSelectAvailableLanguage(language: Language): void {
    if (language !== this.selectedAvailableLanguage) {
      this.selectedAvailableLanguage = language;
      this.languageLevelToAdd = null;
    }
  }

  onSelectAvailableSkill(skill: Skill): void {
    if (skill !== this.selectedAvailableSkill) {
      this.selectedAvailableSkill = skill;
      this.skillLevelToAdd = null;
    }
  }

  addSkill(): void {
    let newUser: User;
    newUser = this.compare.createCopy(this.userToUpdate);
    for(let i: number = 0; i < newUser.userSkills.epoList.length; i++) {
      newUser.userSkills.epoList[i].user = null;
    }
    this.userToUpdate.userSkills.epoList.push(new UserSkill(0, newUser, this.selectedAvailableSkill, this.skillLevelToAdd));
    this.getAvailableSkills();
    this.skillLevelToAdd = null;
    this.selectedAvailableSkill = null;
    console.log(this.userToUpdate);
    //console.log(this.initialUserState);
  }

  removeSkill(): void {
    let userSkill = new UserSkill(this.selectedCurrentSkill.id, this.selectedCurrentSkill.user, this.selectedCurrentSkill.skill, this.selectedCurrentSkill.level);
    let index: number = this.userToUpdate.userSkills.epoList.findIndex(skill => skill.id === userSkill.id);
    if (index > -1) {
      this.userToUpdate.userSkills.epoList.splice(index, 1);
    }
    this.getAvailableSkills();
    this.selectedCurrentSkill = null;
  }

  addLanguage(): void {
    let newUser: User;
    newUser = this.compare.createCopy(this.userToUpdate);
    for(let i: number = 0; i < newUser.userLanguages.epos.length; i++) {
      newUser.userLanguages.epos[i].user = null;
    }
    this.userToUpdate.userLanguages.epos.push(new UserLanguage(0, newUser, this.languageLevelToAdd, this.selectedAvailableLanguage));
    this.getAvailableLanguages();
    this.languageLevelToAdd = null;
    this.selectedAvailableLanguage = null;
  }

  removeLanguage(): void {
    let userLanguage = new UserLanguage(this.selectedCurrentLanguage.id, this.selectedCurrentLanguage.user, this.selectedCurrentLanguage.level, this.selectedCurrentLanguage.language);
    let index: number = this.userToUpdate.userLanguages.epos.findIndex(language => language.id === userLanguage.id);
    if (index > -1) {
      this.userToUpdate.userLanguages.epos.splice(index, 1);
    }
    this.getAvailableLanguages();
    this.selectedCurrentLanguage = null;
  }

  getAvailableSkills(): void {
    this.availableSkills = [];
    this.skills.forEach(skill => {
      let isInside: boolean = false;
      this.userToUpdate.userSkills.epoList.forEach(userskill => { if (userskill.skill.name === skill.name) isInside = true; });
      if (isInside === false) this.availableSkills.push(skill);
    });

  }

  getAvailableLanguages(): void {
    this.availableLanguages = [];
    this.languages.forEach(language => {
      let isInside: boolean = false;
      this.userToUpdate.userLanguages.epos.forEach(userlanguage => { if (userlanguage.language.name === language.name) isInside = true; });
      if (isInside === false) this.availableLanguages.push(language);
    });
  }

  // onUserChange(): void {
  //   if (!this.compare.deepEquals(this.userToUpdate, this.initialUserState)) {
  //     //
  //     //prompt
  //     //if yes:
  //     //  userService.updateUser(userToUpdate)
  //     //else:
  //     //  userToUpdate.updateUser(initialUserState)
  //     //update user
  //     //
  //   }
  // }
  // cancelUpdate(): void {
  //   this.compare.copyInto(this.userToUpdate, this.initialUserState);
  //   this.closeLine.emit('complete')
  // }
}
