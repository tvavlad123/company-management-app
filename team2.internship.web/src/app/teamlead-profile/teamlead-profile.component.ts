import { Component, OnInit } from '@angular/core';
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
import {TeamLead} from 'app/models/teamlead.model';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teamlead-profile',
  templateUrl: './teamlead-profile.component.html',
  styleUrls: ['./teamlead-profile.component.css']
})
export class TeamleadProfileComponent implements OnInit {

  selectedUserSkill: UserSkill;
  selectedUserLanguage: UserLanguage;
  selectedUserSkillAdd: Skill;
  selectedUserSkillLevelAdd: SkillLevel;
  selectedUserLanguageAdd: Language;
  selectedUserLanguageLevelAdd: LanguageLevel;

  user: User = new User(0, "", "", "", "", new Authority(0, ""), "", new Position(0, ""), new Team(0, "", new Department(0, "")), null, null);
  userskills: UserSkill[];
  userlanguages: UserLanguage[];
  positions: Position[];
  selectedPosition: Position = null;
  remainingSkills: Skill[];
  skillLevels: SkillLevel[];
  remainingLanguages: Language[];
  languageLevels: LanguageLevel[];

  showUserSkills: boolean = false;
  showUserLanguages: boolean = false;
  searchUsers: User[];

  inputImage: string;
  showInputImage: boolean = false;


  public teamsLeader: TeamLead[];


  public teams: Team[] = [];
  public selectedTeam: Team;
  public show: boolean = false;
  public department: Department = new Department(null, null);
  public memberName: string;
  public userName: string;
  username2:string;
  public name: string;
  public myTeam: Team = new Team(null, "", new Department(0, ""));
  public depts: Department[] = new Array<Department>();
  public showCreate: boolean = false;
  public dept: Department = new Department(null, null);
  public teamMembers: User[] = [];
  public showMembers: boolean = false;
  public showUsers: boolean = false;
  public selectedMember: User;
  public showAddButton: boolean = false;
  public showRemoveButton: boolean = false;
  public allUsers: User[] = [];
  public selectedUser: User;



  showUpdateControls(): void {
    console.log(this.selectedTeam.department);
    this.show = true;
    this.showMembers = false;
    this.showUsers = false;
    this.showAddButton = false;
    this.showRemoveButton = false;
    this.hideCreateControls();

    this.dataService.getTeamsLead(this.authenticationService.getAuthority().id).subscribe(r => this.teamsLeader = r);
    this.getDepartments();

  }

  hideUpdateControls(): void {
    this.show = false;
  }

  showCreateControls(): void {
    this.showCreate = true;
    this.showMembers = false;
    this.showUsers = false;
    this.show = false;
    this.showRemoveButton = false;
    this.showAddButton = false;
    this.hideUpdateControls();
  }

  hideCreateControls(): void {
    this.showCreate = false;
  }

  showMembersControl(): void {
    this.showMembers = true;
    this.showUsers = false;
    this.showAddButton = true;
    this.showRemoveButton = true;
  }

  showUsersControl(): void {
    this.showUsers = true;
    this.showMembers = false;
    this.showAddButton = true;
    this.showRemoveButton = true;
  }


  constructor(private dataService: DataService,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService,
    private router: Router ) { }

  ngOnInit() {
    this.getUserById();
    this.getUserSkills();
    this.getUserLanguages();
    this.getUserPositions();
    this.getRemainingSkills();
    this.getRemainingLanguages();
    this.getSkillLevels();
    this.getLanguageLevels();

    this.dataService.getTeamsLead(this.authenticationService.getId()).subscribe(r => this.teamsLeader = r);
    this.getDepartments();
  }

  getUserById(): void {
    this.dataService.getUser(this.authenticationService.getId()).subscribe(u => this.user = u);
  }

  getUserSkills(): void {
    this.dataService.getUserSkills(this.authenticationService.getId()).subscribe(uskills => this.userskills = uskills);
  }

  getUserLanguages(): void {
    this.dataService.getUserLanguages(this.authenticationService.getId()).subscribe(ulanguages => this.userlanguages = ulanguages);
  }


  getUserPositions(): void {
    this.dataService.getPositions().subscribe(pos => this.positions = pos);
  }


  getRemainingSkills(): void {
    this.dataService.getRemainingSkills(this.authenticationService.getId()).subscribe(remskills => this.remainingSkills = remskills);
  }

  getRemainingLanguages(): void {
    this.dataService.getRemainingLanguages(this.authenticationService.getId()).subscribe(remlang => this.remainingLanguages = remlang);
  }

  getSkillLevels(): void {
    this.dataService.getSkillLevels().subscribe(skilllev => this.skillLevels = skilllev);
  }

  getLanguageLevels(): void {
    this.dataService.getLanguageLevels().subscribe(languagelev => this.languageLevels = languagelev);
  }

  showSkillDetails(): void {
    this.showUserSkills = true;
    this.getRemainingSkills();
    this.getSkillLevels();
  }

  closeSkillDetails(): void {
    this.showUserSkills = false;
  }


  showLanguageDetails(): void {
    this.showUserLanguages = true;
    this.getRemainingLanguages();
    this.getLanguageLevels();
  }


  closeLanguageDetails(): void {
    this.showUserLanguages = false;
  }


  onSelect(userskill: UserSkill): void {
    this.selectedUserSkill = userskill;
  }


  deleteUserSkill(): void {
    console.log(this.selectedUserSkill);
    this.dataService.deleteUserSkill(this.selectedUserSkill).subscribe(r => {
      this.getUserSkills();
      this.selectedUserSkill = null;
    });
  }


  onSelectLanguage(userlanguage: UserLanguage): void {
    this.selectedUserLanguage = userlanguage;
  }


  deleteUserLanguage(): void {
    console.log(this.selectedUserLanguage);
    this.dataService.deleteUserLanguage(this.selectedUserLanguage).subscribe(r => {
      this.getUserLanguages();
      this.selectedUserLanguage = null;
    });
  }


  saveUserSkill(): void {
    console.log(this.selectedUserSkillAdd);
    console.log(this.selectedUserSkillLevelAdd);
    let userSkill = new UserSkill(null, this.user, this.selectedUserSkillAdd, this.selectedUserSkillLevelAdd);
    userSkill.id = 0;
    this.selectedUserSkillAdd = null;
    this.selectedUserSkillLevelAdd = null;
    console.log(userSkill);
    console.log(JSON.stringify(userSkill));
    this.dataService.addUserSkill(userSkill).subscribe(r => {
      console.log(r);
      this.getUserSkills();

    });
  }


  saveUserLanguage(): void {
    console.log(this.selectedUserLanguageAdd);
    console.log(this.selectedUserLanguageLevelAdd);

    let userLanguage = new UserLanguage(0, this.user, this.selectedUserLanguageLevelAdd, this.selectedUserLanguageAdd);
    userLanguage.id = 0;
    console.log(JSON.stringify(userLanguage));
    this.dataService.addUserLanguage(userLanguage).subscribe(r => {
      console.log(r);
      this.getUserLanguages();
    });

    this.selectedUserLanguageAdd = null;
    this.selectedUserLanguageLevelAdd = null;

  }

  saveUserPosition(): void {
    this.user.position = this.selectedPosition;
    console.log(this.selectedPosition);
    this.dataService.create(this.user).subscribe(response => {
      console.log(JSON.stringify(response));
    });
  }


  onKeyUp(): void {
    console.log("MERGE")
    this.searchMembers();
  }

  searchMembers(): void {
    if (this.username2) {
      this.dataService.getUsersByName(this.username2)
        .subscribe(searchUsernames => this.searchUsers = searchUsernames);
      console.log(this.searchUsers);
    }
    else
      this.searchUsers = null;
  }

  onChange(position) {
    console.log("onChange", position.name);
    this.selectedPosition = position;
    this.saveUserPosition();
  }

  changeListener($event): void {
    this.readThis($event.target);
  }

  readThis(inputValue: any): void {
    var file: File = inputValue.files[0];
    var myReader: FileReader = new FileReader();

    myReader.onloadend = (e) => {
      this.inputImage = myReader.result;
      this.user.photoLocation = this.inputImage;
    }
    myReader.readAsDataURL(file);
    setTimeout(() => { this.dataService.updateUser(this.user).subscribe(); }, 4000)

  }


  showMyImage(): void {
    this.showInputImage = true;
    console.log(this.inputImage);
  }

  getTrustedSource2(html: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(html);
  }

  getTrustedSource(html: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + html);
  }

  goToProfile(searchUser: User): void {
    this.router.navigate(['searchuser/' + searchUser.id]);
  }


  /* From teamprofile*/


  onSelectTeam(team: Team): void {
    if (this.selectedTeam != team) {
      this.showMembers = false;
    }
    this.selectedTeam = team;
    this.selectedMember = null;
    this.selectedUser = null;
    this.showUsers = true;
    this.showMembers = true;
    this.viewAllMembers();
    this.viewTeam();
  }


  onUserSelect(user: User): void {
    this.selectedUser = user;
    this.selectedMember = undefined;
  }


  getDepartments(): void {
    this.dataService.getDepartments().subscribe(r => this.depts = r)
  }


  viewTeam(): void {
    this.showMembersControl();
    this.showUsers = true;
    this.show = true;
    this.showAddButton = true;
    this.showRemoveButton = true;
    this.dataService.getUsersByTeam(this.selectedTeam).subscribe(response => {
      this.teamMembers = response;
    });
    this.hideUpdateControls();
    this.hideCreateControls();
  }

  viewAllMembers(): void {
    this.showUsersControl();
    this.showAddButton = true;
    this.showRemoveButton = true;
    this.selectedMember = null;
    this.dataService.getNonMembers(this.selectedTeam.id).subscribe(response => {
      this.allUsers = response;
      console.log(this.allUsers);
    });
    this.hideUpdateControls();
    this.hideCreateControls();
  }


  searchMembers2(): void { 
    if (this.memberName) {
      this.dataService.getUsersByNameFromTeam(this.memberName, this.selectedTeam)
        .subscribe(gotMembers => this.teamMembers = gotMembers);
    } else {
      this.dataService.getUsersByTeam(this.selectedTeam)
        .subscribe(gotMembers => this.teamMembers = gotMembers);
    }
  }


  searchUsers2(): void {
    if (this.userName) {
      this.dataService.getUsersByNameNotFromTeam(this.userName, this.selectedTeam)
        .subscribe(gotMembers => this.allUsers = gotMembers);
    } else {
      this.dataService.getNonMembers(this.selectedTeam.id)
        .subscribe(gotMembers => this.allUsers = gotMembers);
    }
  }


  onKeyUp2(): void {
    this.searchMembers2();
    this.searchUsers2();
  }

   goToProfile2(member:TeamLead): void{
    this.router.navigate(['searchuser/' + member.id]);
  }


}
