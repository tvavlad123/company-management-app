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
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';


@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})


export class UserprofileComponent implements OnInit {

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

  username: string;
  searchUsers: User[];

  inputImage: string;
  saveVacationBool: boolean = true;
  showInputImage: boolean = false;

  gNrReqUserH: number;
  gNrReqUserV: number;
  gNrReqUnresH: number;
  gNrReqUnresV: number;

  constructor(private dataService: DataService,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService,
    private router: Router,) { }


saveVacation(event){
  this.saveVacationBool = false;
}
  ngOnInit() {
    this.getUserById();
    this.getUserSkills();
    this.getUserLanguages();
    this.getUserPositions();
    this.getNumberOfHomeOffReqUserYear();
    this.getNumberOfVacReqUserYear();
    this.getNumberOfHomeOffUnres();
    this.getNumberOfVacUnres();
    this.getRemainingSkills();
    this.getRemainingLanguages();
    this.getSkillLevels();
    this.getLanguageLevels();
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

  getNumberOfHomeOffReqUserYear(): void{
    this.dataService.findNrOfHomeOfficeRequestsAcceptedForUserPerYear(this.authenticationService.getId()).subscribe(gnrrequser => {
      this.gNrReqUserH = gnrrequser;
      if(isNaN(this.gNrReqUserH))
        {
          this.gNrReqUserH = 0;
        }

    });
  }

  getNumberOfVacReqUserYear(): void{
    this.dataService.findNrOfVacationRequestsAcceptedForUserPerYear(this.authenticationService.getId()).subscribe(gnrrequser => {
      this.gNrReqUserV = gnrrequser;
      if(isNaN(this.gNrReqUserV))
        {
          this.gNrReqUserV = 0;
        }

    });
  }

  getNumberOfHomeOffUnres(): void{
    this.dataService.findNrOfHomeOfficeRequestsUnresolved(this.authenticationService.getId()).subscribe(gnrhounres => {
      this.gNrReqUnresH = gnrhounres;
      if(isNaN(this.gNrReqUnresH))
        {
          this.gNrReqUnresH = 0;
        }

    });
  }

  getNumberOfVacUnres(): void{
    this.dataService.findNrOfVacationRequestsUnresolved(this.authenticationService.getId()).subscribe(gnrhounres => {
      this.gNrReqUnresV = gnrhounres;
      if(isNaN(this.gNrReqUnresV))
        {
          this.gNrReqUnresV = 0;
        }

    });
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
    this.searchMembers();
  }


  searchMembers(): void {
    if (this.username) {
      this.dataService.getUsersByName(this.username)
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

  goToProfile(searchUser:User):void{
     this.router.navigate(['searchuser/' + searchUser.id]);
  }

}
