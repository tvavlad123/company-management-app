import { Component, OnInit } from '@angular/core';
import {DataService} from '../adminpage/shared/data.service';
import {AuthenticationService} from '../security/authentication.service';
import {Team} from '../models/team.model';
import {User} from '../models/user.model';
import {TableEntry} from '../models/tableentry.model';
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { VacationRequest } from "app/models/vacationrequest.model";
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-calendarcreatereq',
  templateUrl: './calendarcreatereq.component.html',
  styleUrls: ['./calendarcreatereq.component.css']
})
export class CalendarcreatereqComponent implements OnInit {

  homeOfficeRequest: HomeOfficeRequest;
  vacationRequest: VacationRequest = new VacationRequest(null,null,null,null,null,null,null);
  year: number;
  month: string;
  monthnr: number;
  daysInM: number;
  currentDay: number=1;
  items: any[];
  dayInW:number;
  user_id:number;
  users: any[] = new Array();
  tableEntry:TableEntry = new TableEntry(null,null);
  userActive: any;
  public inputImage: string;

  constructor(private userService: DataService, private authenticationService: AuthenticationService,private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.getUserId();
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

getUserId():void {
  this.user_id=this.authenticationService.getId21();
  this.userService.getUser(this.user_id).subscribe(response=> {
    this.userActive=response;
    this.userService.getTableEntryForUser(this.user_id,this.monthnr+1,this.year)
    .subscribe(response => {
      this.userActive.tableEntry = response; 
      console.log(this.userActive.tableEntry);
    });
  });
}


selectCellUser(index: number) {
  this.userActive.tableEntry.requestsImplementation[index].selected = !this.userActive.tableEntry.requestsImplementation[index].selected;
  
}

submitRequest() {
  this.userActive.tableEntry.requestsImplementation.filter(item => item.selected).forEach(element => {
    // this.userService.sendRequest(new Date(element.year, element.month, element.dayInMonth), requestType: this.requestType)      
  });
}


createHomeOfficeRequest() {
  this.homeOfficeRequest.user_id=this.userActive.id;
  this.userService.findNumberOfDaysLeftAvailableToTake(this.homeOfficeRequest.user_id, this.homeOfficeRequest.start_date, this.homeOfficeRequest.end_date, this.homeOfficeRequest.half_day.id).subscribe(response => {
    console.log(JSON.stringify(response));
    if (response < 0) {
      alert("HomeOffice request not sent! You do not have so many days left!");
    } else {
      this.userService.saveHomeOfficeRequest(this.homeOfficeRequest).subscribe(response2 => {
        console.log(JSON.stringify(response2));
        if (response2.id > 0) {
          alert("HomeOffice request succesfully sent! You still have " + response + " days left!");
        }
      });
    }
  });
}

createVacationRequest() {
  this.vacationRequest.user_id=this.userActive.id;
  this.userService.findNrOfAvailbleVacationDaysToTake(this.vacationRequest.user_id, this.vacationRequest.start_date, this.vacationRequest.end_date).subscribe(response => {
    console.log(JSON.stringify(response));
    if (response < 0) {
      alert("Vacation request not sent! You do not have so many days left!");
    } else {
      this.userService.saveVacationRequest(this.vacationRequest).subscribe(response2 => {
        console.log(JSON.stringify(response2));
        if (response2.id > 0) {
          alert("Vacation request succesfully sent! You still have " + response + " days left!");
        }
      });
    }
  });
}

setHalfDay(id: number): void {
  console.log("MERGE ->>>>>>" + id)
  this.homeOfficeRequest.half_day.id = id;
}

myFunction():void {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}

myFunction1():void {
  var popup = document.getElementById("myPopup1");
  popup.classList.toggle("show");
}

changeListener($event): void {
  this.readThis($event.target);
}

readThis(inputValue: any): void {
  var file: File = inputValue.files[0];
  var myReader: FileReader = new FileReader();

  myReader.onloadend = (e) => {
    this.inputImage = myReader.result;
    this.vacationRequest.picture = this.inputImage;
    console.log(myReader.result)
  }
  myReader.readAsDataURL(file);
  //setTimeout(() => { this.dataService.saveVacationRequest(this.vacationRequest).subscribe(); }, 4000)

}

getTrustedSource2(html: string) {
  return this.sanitizer.bypassSecurityTrustResourceUrl(html);
}

getTrustedSource(html: string) {
  return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + html);
}

}
