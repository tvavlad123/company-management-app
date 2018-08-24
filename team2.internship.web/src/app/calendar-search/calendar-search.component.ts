import { Component, OnInit, Input } from '@angular/core';
import {DataService} from '../adminpage/shared/data.service';
import {AuthenticationService} from '../security/authentication.service';
import {Team} from '../models/team.model';
import {User} from '../models/user.model';
import {TableEntry} from '../models/tableentry.model';
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { VacationRequest } from "app/models/vacationrequest.model";

@Component({
  selector: 'app-calendar-search',
  templateUrl: './calendar-search.component.html',
  styleUrls: ['./calendar-search.component.css']
})
export class CalendarComponentSearch implements OnInit {

  year: number;
  month: string;
  monthnr: number;
  daysInM: number;
  currentDay: number=1;
  items: any[];
  dayInW:number;
  team:Team = new Team(null,null,null);
  user_id:number;
  nrUsers:number;
  employeeBool:boolean = false;
  teamCoord:boolean = false;
  teamLead:boolean = false;
  userNames: string[] = new Array();
  users: any[] = new Array();
  team_id:number;
  tableEntry:TableEntry = new TableEntry(null,null);
  userActive: any;
  @Input() inputId:any;
  teams:any[] = new Array();
  abcd: any;
  homeOfficeRequest: HomeOfficeRequest;
  vacationRequest: VacationRequest;
  allUsers = [];

  constructor(private userService: DataService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.checkAuthority();
    this.getCurrentDate();
    this.daysInM=this.daysInMonth(this.monthnr,this.year);
    this.dayInW=this.dayInWeek();
    this.ale();
  }

  ale(): void{
      this.items = [{day:"Mo"},{day:"Tu"},{day:"We"},{day:"Th"},{day:"Fr"}];
      this.items = this.items.concat(this.items).concat(this.items).concat(this.items).concat(this.items);  
  }

  getCurrentDate(): void{
    this.year=new Date().getFullYear() ;
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    this.monthnr=new Date().getMonth();
    this.month=monthNames[this.monthnr];
  }

  nextMonth(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==11){
      this.year++;
      this.monthnr=0;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr++;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getTeamLeadById();
  }

  nextMonthUser(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==11){
      this.year++;
      this.monthnr=0;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr++;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getUserId();
  }

  nextMonthTeamCoord(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==11){
      this.year++;
      this.monthnr=0;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr++;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getTeamById();
  }

  prevMonth(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==0){
      this.year--;
      this.monthnr=11;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr--;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getTeamLeadById();
  }

  prevMonthUser(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==0){
      this.year--;
      this.monthnr=11;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr--;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getUserId();
  }

  prevMonthTeamCoord(): void{
    var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
    if (this.monthnr==0){
      this.year--;
      this.monthnr=11;
      this.month=monthNames[this.monthnr];
    }
    else{
      this.monthnr--;
      this.month=monthNames[this.monthnr];
    }
    this.dayInW=this.dayInWeek();
    this.getTeamById();
  }


 daysInMonth(month,year): number {
      return new Date(year, month, 0).getDate();
  }

  dayInWeek(): number{
    var date= new Date(this.year, this.monthnr, 1);
    var dayOfWeek=date.getDay();
    if (dayOfWeek==6 || dayOfWeek==7 ){
      dayOfWeek-=7;
    }
    return dayOfWeek;
  }

  getNrOfUsersFromT(): void{
   this.userService.getNrOfUsersFromATeam(this.team.id).subscribe(response =>  this.nrUsers=response);
  }

  getUserId():void {
      this.user_id=this.inputId;
      console.log(this.inputId+" <IDUL")
      this.userService.getUser(this.user_id).subscribe(response=> {
        this.userActive=response;
        this.userService.getTableEntryForUser(this.user_id,this.monthnr+1,this.year)
        .subscribe(response => {
          this.userActive.tableEntry = response; 
          console.log(this.userActive.tableEntry);
        });
      });
  }

  getUsersFromTeam():void {
    this.userService.getUsersByTeam(this.team).subscribe(response => {this.users=response});
  }

  checkAuthority(): void{
    /*
    var authority=this.authenticationService.getAuthority();
    if (authority == "ROLE_EMPLOYEE"){
      this.employeeBool=true;
      this.getUserId();
    }    
    else if(authority == "ROLE_TEAM_COORD"){
      this.teamCoord=true;
      this.getTeamById();
    }
    else if(authority == "ROLE_TEAM_LEAD"){
      this.teamLead=true;
      this.getTeamLeadById();
    }*/
    this.employeeBool=true;
    this.getUserId();
  }

  getTeamById():void {
    this.user_id=this.authenticationService.getId21();

    this.userService.getUser(this.user_id).subscribe(response=> {
      this.userActive=response;
      this.userService.getTableEntryForUser(this.user_id,this.monthnr+1,this.year)
      .subscribe(response => {
        this.userActive.tableEntry = response; 
        console.log(this.userActive.tableEntry);
      });
    });

    this.userService.getUser(this.user_id).subscribe(response=> {
      this.userActive=response;
      this.team_id=this.userActive.team.id;
      this.userService.getTeamById(this.team_id).subscribe(response => {
      this.team=response;
      this.userService.getUsersByTeam(this.team).subscribe(response => {
        this.users=response;
        this.getTableEntryForUser();
      });
    } );
  })
  }

  getTeamLeadById():void{
    this.user_id=this.authenticationService.getId21();

    this.userService.getUser(this.user_id).subscribe(response=> {
      this.userActive=response;
      this.userService.getTableEntryForUser(this.user_id,this.monthnr+1,this.year)
      .subscribe(response => {
        this.userActive.tableEntry = response; 
        console.log(this.userActive.tableEntry);
      });
    });

    var teamLeads;
    this.userService.getTeamsLead(this.user_id).subscribe(response => {
      teamLeads=response;
      teamLeads.forEach((tm, ind)=>
      {
        this.teams[ind]=tm.team;
      }
    )
      console.log(this.teams);
      this.allUsers = [];
      this.teams.forEach((team, index)=>{
        this.userService.getUsersByTeam(team).subscribe(response=> {
          this.teams[index].users=response;
          response.forEach(item => {
            this.allUsers.push({
              team: team,
              user: item
            })
          })
         
          this.abcd = this.teams[0].users[0];
          this.getTableEntryForUserTL(this.teams[index].users);});
      })
    });
  }

  getTableEntryForUser(): void{
    this.users.forEach((user, index) => {
      this.userService.getTableEntryForUser(user.id,this.monthnr+1,this.year)
      .subscribe(response => {
        this.users[index].tableEntry = response; 
        console.log(this.users[index].tableEntry);
      });
    });  
  }

  getTableEntryForUserTL(users:any): void{
    users.forEach((user, index) => {
      this.userService.getTableEntryForUser(user.id,this.monthnr+1,this.year)
      .subscribe(response => {
        users[index].tableEntry = response; 
      });
    });  
  }

  selectCell(userIndex: number, index: number) {
    this.users[userIndex].tableEntry.requestsImplementation[index].selected = !this.users[userIndex].tableEntry.requestsImplementation[index].selected;
  }
}
