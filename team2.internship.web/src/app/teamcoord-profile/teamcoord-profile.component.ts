import { Component, OnInit } from '@angular/core';
import { DataService } from '../adminpage/shared/data.service';
import { User } from '../models/user.model';
import { Skill } from "app/models/skill.model";
import { UserSkill } from "app/models/userskill.model";
import { Department } from "app/models/department.model";
import { Team } from "app/models/team.model";
import { Authority } from "app/models/authority.model";
import { VacationRequest } from "app/models/vacationrequest.model";
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { Position } from "app/models/position.model";
import { UserLanguage } from "app/models/userlanguage.model";
import { SkillLevel } from 'app/models/skilllevel.model';
import { Language } from 'app/models/language.model';
import { HORequest } from 'app/models/horequest.model';
import { VRequest } from 'app/models/vrequest.model';
import { LanguageLevel } from 'app/models/languagelevel.model';
import { AuthenticationService } from '../security/authentication.service';
import { TeamLead } from 'app/models/teamlead.model';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teamcoord-profile',
  templateUrl: './teamcoord-profile.component.html',
  styleUrls: ['./teamcoord-profile.component.css']
})
export class TeamcoordProfileComponent implements OnInit {


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
  searchUsers2: User[];

  inputImage: string;
  showInputImage: boolean = false;


  public teamsLeader: TeamLead[];


  public teams: Team[] = [];
  public selectedTeam: Team;
  public department: Department = new Department(null, null);
  public memberName: string;
  public userName: string;
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
  gNrReqUserH: number;
  gNrReqUserV: number;
  gNrReqUnresH: number;
  gNrReqUnresV: number;



  showUpdateControls(): void {
    console.log(this.selectedTeam.department);
    this.showMembers = false;
    this.showUsers = false;
    this.showAddButton = false;
    this.showRemoveButton = false;
  }


  public username2: string;



  constructor(private dataService: DataService,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    this.getUserById();
    this.getUserSkills();
    this.getUserLanguages();
    this.getUserPositions();
    this.getRemainingSkills();
    this.getRemainingLanguages();
    this.getSkillLevels();
    this.getLanguageLevels();
    this.getNumberOfHomeOffReqUserYear();
    this.getNumberOfVacReqUserYear();
    this.getNumberOfHomeOffUnres();
    this.getNumberOfVacUnres();

    this.dataService.getTeamsLead(this.authenticationService.getId()).subscribe(r => this.teamsLeader = r);

  }

  getUserById(): void {
    this.dataService.getUser(this.authenticationService.getId()).subscribe(u => {
      this.user = u;
      this.loadRequests();
    });
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

  homeOfficeRequestsUnresolved:HomeOfficeRequest[];
  vacationRequestsUnresolved: VacationRequest[];
  horequests: HORequest[] = new Array<HORequest>();
  vrequests: VRequest[] = new Array<VRequest>();

  loadRequests(){
    this.dataService.findVacationRequestsUnresolvedForTeamCoord(this.user.team.id).subscribe(res=>{
      this.vacationRequestsUnresolved=res;
      this.vrequests=new Array<VRequest>();
      for(let i=0; i<this.vacationRequestsUnresolved.length;i++)
        {
          let v = new VRequest(this.vacationRequestsUnresolved[i],this.getUserName(this.vacationRequestsUnresolved[i].user_id));
          this.dataService.getUser(this.vacationRequestsUnresolved[i].user_id).subscribe(res=>{
            v.user=res;
            this.vrequests.push(v);
            // console.log(v);
          });
          
        }
      
    });
    this.dataService.findHomeOfficeRequestsUnresolvedForTeamCoord(this.user.team.id).subscribe(res=>{
      this.homeOfficeRequestsUnresolved=res;
      this.horequests=new Array<HORequest>();
      for(let i=0; i<this.homeOfficeRequestsUnresolved.length;i++)
        {
          let ho = new HORequest(this.homeOfficeRequestsUnresolved[i],this.getUserName(this.homeOfficeRequestsUnresolved[i].user_id));
          this.dataService.getUser(this.homeOfficeRequestsUnresolved[i].user_id).subscribe(res=>{
            ho.user=res;
            this.horequests.push(ho);
            // console.log(ho);
          });
          
        }
        // console.log(this.horequests[0].user.firstName+" < TOATE REQUESTURILE");
    });
  }

  acceptHORequest(request:HORequest){
    request.request.status_id.id=2;
    // console.log(request);
    this.dataService.updateHomeOfficeRequestStatus(request.request).subscribe(res => this.loadRequests());
    
  }

  declineHORequest(request:HORequest){
    request.request.status_id.id=3;
    // console.log(request);
    this.dataService.updateHomeOfficeRequestStatus(request.request).subscribe(res=> this.loadRequests());
    
  }

  acceptVRequest(request:VRequest){
    request.request.status_id.id=2;
    // console.log(request);
    this.dataService.updateVacationRequestStatus(request.request).subscribe(res => this.loadRequests());
    
  }

  declineVRequest(request:VRequest){
    request.request.status_id.id=3;
    // console.log(request);
    this.dataService.updateVacationRequestStatus(request.request).subscribe(res=> this.loadRequests());
    
  }

  getUserName(nr:number):any{
    let user: User;
    this.dataService.getUser(nr).subscribe(res=>{
      user=res;  
      return user;
    })
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

  goToProfile(searchUser: User): void {
    this.router.navigate(['searchuser/' + searchUser.id]);
  }



  /* From teamprofile*/

  loadMembers(){
    this.dataService.getUsersByTeam(this.user.team).subscribe(response => {
      this.teamMembers = response;
      console.log(this.teamMembers)
    });
  }


  onKeyUp2(): void {
    this.searchMembers2();
  }


  searchMembers2(): void {
    if (this.username2) {
      this.dataService.getUsersByNameFromTeam(this.username2, this.user.team)
        .subscribe(searchUsernames => this.teamMembers = searchUsernames);
    }
    else
      this.loadMembers();
  }
  
  goToProfile2(member: TeamLead): void {
    this.router.navigate(['searchuser/' + member.id]);
  }


}