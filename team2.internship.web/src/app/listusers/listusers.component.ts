import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from '../models/userList.model';
import { DataServiceList } from '../adminpage/shared/dataList.service';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UpdateUserTableComponent } from './update-user-table/update-user-table.component'
import { Router } from "@angular/router";
import { Team } from '../models/team.model';
import { TeamLead } from '../models/teamlead.model';
import { Authority } from "app/models/authority.model";
import { Position } from "app/models/position.model";
import { Department } from "app/models/department.model";
import { UserSkill } from "app/models/userskillList.model";
import { UserLanguage } from "app/models/userlanguageList.model";
import { Skill } from "app/models/skill.model";
import { Language } from "app/models/language.model";
import { SkillLevel } from "app/models/skilllevel.model";

import { CompareService } from "../adminpage/shared/compare.service";

@Component({
  selector: 'app-listusers',
  templateUrl: './listusers.component.html',
  styleUrls: ['./listusers.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ListusersComponent implements OnInit {

  initialUserState: User = null;
  updatePromptResponse: boolean;
  users: User[] = [];
  selectedUser: User = null;
  userToReset: User = null;
  userToDelete: User = null;
  rowChanged: boolean = false;

  constructor(private userService: DataServiceList,
    private router: Router,
    private compare: CompareService) { }

  ngOnInit(): void {
    this.getUsers();
    this.getAuthorities();
    this.getPositions();
    this.getTeams();
    this.getDepartments();
    this.getSkills();
    this.getLanguages();
    this.getSkillLevels();
    this.getLanguageLevels();
  }

  authorities: Authority[] = [];
  getAuthorities(): void {
    this.userService.getAuthorities().subscribe(r => { this.authorities = r; console.log(r) });
  }
  positions: Position[] = [];
  getPositions(): void {
    this.userService.getPositions().subscribe(r => this.positions = r);
  }
  teams: Team[] = [];
  getTeams(): void {
    this.userService.getTeams().subscribe(r => this.teams = r);
  }
  departments: Department[] = [];
  getDepartments(): void {
    this.userService.getDepartments().subscribe(r => this.departments = r);
  }
  skills: Skill[] = [];
  getSkills(): void {
    this.userService.getSkills().subscribe(r => this.skills = r);
  }
  languages: Language[] = [];
  getLanguages(): void {
    this.userService.getLanguages().subscribe(r => this.languages = r);
  }
  skillLevels: SkillLevel[] = new Array<SkillLevel>();
  getSkillLevels(): void {
    this.userService.getSkillLevels().subscribe(res => this.skillLevels = res);
  }
  languageLevels: SkillLevel[] = new Array<SkillLevel>();
  getLanguageLevels(): void {
    this.userService.getLanguageLevels().subscribe(res => this.languageLevels = res);
  }

  closeLine(event) {
    this.selectedUser = null;
    this.initialUserState = null;
  }

  updateUserToReset(user: User) {
    this.userToReset = user;
  }
  resetPassword() {
    this.userService.resetPassword(this.userToReset.id).subscribe(r => console.log(r));
  }
  cancelReset() {
    this.userToReset = null;
  }

  updateUserToDelete(user: User) {
    this.userToDelete = user;
  }
  deleteUser() {
    if (this.userToDelete.authority.authorityType !== "Admin") {
      this.userService.deleteUser(this.userToDelete.id).subscribe(r => { console.log(r); this.getUsers(); });
    }
    else {
      alert("The user you are trying to delete is an Admin!")
    }
  }
  cancelDelete() {
    this.userToDelete = null;
  }

  onSelect(user: User): void {
    if(this.selectedUser == null && this.initialUserState == null) {
      //suntem in stare initiala sau imediat dupa ce s-a efectuat sau anulat un update
      this.selectedUser = user;
      this.initialUserState = this.compare.createCopy(this.selectedUser);
    }
    else if (this.selectedUser != null && user !== this.selectedUser && !this.compare.equals(this.initialUserState, this.selectedUser)) {
      //un rand este selectat (comp deschisa), s-a dat click pe alt rand, s-au facut schimbari
      this.updatePromptResponse = confirm("Do you want to keep the changes made to the user?");
      if (this.updatePromptResponse) {
        this.updateUser();
      }
      else {
        this.cancelUpdate();
      }
    }
    else if(this.selectedUser != null && user !== this.selectedUser ) {
      //un rand este selectat, s-a dat click pe alt rand, nu s-au facut schimbari(else)
      this.selectedUser = user;
      this.initialUserState = this.compare.createCopy(this.selectedUser);
    }
  }

  updateUser(): void {
    for (let i = 0; i < this.selectedUser.userLanguages.epos.length; i++) {
      this.selectedUser.userLanguages.epos[i].user = null;
    }
    for (let i = 0; i < this.selectedUser.userSkills.epoList.length; i++) {
      this.selectedUser.userSkills.epoList[i].user = null;
    }
    this.userService.updateUser(this.selectedUser).subscribe(r => {
      this.closeLine(event);
    });
  }

  cancelUpdate(): void {
    this.compare.restore(this.selectedUser, this.initialUserState);
    this.closeLine(event);
  }

  getUsers(): void {
    this.users = [];
    this.userService.getUsers().subscribe(gotUsers => {
      this.users = gotUsers;
      console.log(this.users);
    });
  }
}