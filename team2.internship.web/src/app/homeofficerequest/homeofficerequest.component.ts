import { Component, OnInit, ViewEncapsulation, OnChanges } from '@angular/core';
import { DataService } from "app/adminpage/shared/data.service";
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { HalfDay } from "app/models/halfday.model";
import { Status } from "app/models/status.model";
import { DatePipe } from '@angular/common';
import { AuthenticationService } from "app/security/authentication.service";


@Component({
  selector: 'app-homeofficerequest',
  templateUrl: './homeofficerequest.component.html',
  styleUrls: ['./homeofficerequest.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class HomeOfficeRequestComponent implements OnInit {

  public homeOfficeRequest: HomeOfficeRequest = new HomeOfficeRequest(null, this.authenticationService.getId(), null, null, new HalfDay(3, null), new Status(1, null));
  public today: Date = new Date();
  public showHalfDay: boolean = false;
  public showsaveRequest: boolean = false;
  public alert1: boolean = false;
  public alert2: boolean = false;
  public alert3: boolean = false;
  public alert4: boolean = false;
  public alert5: boolean = false;
  public alert6: boolean = false;
  public result: number;

  // https://stackoverflow.com/questions/35144821/angular-2-4-use-pipes-in-services-and-components
  constructor(private dataService: DataService, private datePipe: DatePipe, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    console.log(this.datePipe.transform(Date.now(), "yyyy-MM-dd:hh-mm-ss"))
    this.today = new Date(this.datePipe.transform(Date.now(), "yyyy-MM-dd"));
    console.log(this.today);
  }

  createHomeOfficeRequest() {
    if (this.homeOfficeRequest.start_date === null) {
      // alert("Please set start date!");
      this.alert1 = true;
      return;
    }
    if (this.homeOfficeRequest.end_date === null) {
      // alert("Please set end date!");
      this.alert1 = true;
      return;
    }
    // console.log("today" + this.today)
    if (this.homeOfficeRequest.start_date.getFullYear < this.today.getFullYear) {
      // alert("Start date cannot be in the past!");
      this.alert2 = true;
      return;
    }
    if ((this.homeOfficeRequest.start_date.getFullYear <= this.today.getFullYear) && (this.homeOfficeRequest.start_date.getMonth < this.today.getMonth)) {
      // alert("Start date cannot be in the past!");
      this.alert2 = true;
      return;
    }
    if ((this.homeOfficeRequest.start_date.getFullYear <= this.today.getFullYear) && (this.homeOfficeRequest.start_date.getMonth <= this.today.getMonth) && (this.homeOfficeRequest.start_date.getDay < this.today.getDay)) {
      // alert("Start date cannot be in the past!");
      this.alert2 = true;
      return;
    }

    console.log(this.homeOfficeRequest.start_date.getDate + " THIS IS REQUEST")
    console.log(this.today.getDate + " THIS IS TODAY")

    if (this.homeOfficeRequest.start_date > this.homeOfficeRequest.end_date) {
      // alert("Invalid date! Start date must be before end date or should equal end date :)");
      this.alert3 = true;
      return;
    }
    console.log("half day id" + this.homeOfficeRequest.half_day.id)
    if (this.homeOfficeRequest.start_date != this.homeOfficeRequest.end_date && (this.homeOfficeRequest.half_day.id === 1 || this.homeOfficeRequest.half_day.id === 2)) {
      // alert("Half day can be taken only if startDate and endDate are the same");
      this.alert4 = true;
      return;
    }
    this.dataService.findNumberOfDaysLeftAvailableToTake(this.homeOfficeRequest.user_id, this.homeOfficeRequest.start_date, this.homeOfficeRequest.end_date, this.homeOfficeRequest.half_day.id).subscribe(response => {
      console.log(JSON.stringify(response));
      this.result = response;
      if (response < 0) {
        // alert("Home office request not sent! You do not have so many days left!");
        this.alert5 = true;
      } else {
        this.dataService.saveHomeOfficeRequest(this.homeOfficeRequest).subscribe(response2 => {
          console.log(JSON.stringify(response2));
          if (response2.id > 0) {
            // alert("Home office request succesfully sent! You still have " + response + " days left!");
            this.alert6 = true;
          }
        });
      }
    });
  }
  setHalfDay(id: number): void {
    console.log("MERGE ->>>>>>" + id)
    this.homeOfficeRequest.half_day.id = id;
  }

  checkDay(): boolean {
    if (this.homeOfficeRequest.start_date === null) {
      return false;
    }
    if (this.homeOfficeRequest.start_date != this.homeOfficeRequest.end_date) {
      return false;
    }
    return true;
  }

  startDateChanged() {
    if (this.homeOfficeRequest.start_date != null && this.homeOfficeRequest.end_date != null) {
      this.showsaveRequest = true;
      if (this.homeOfficeRequest.start_date != this.homeOfficeRequest.end_date) {
        this.showHalfDay = false;
        this.homeOfficeRequest.half_day.id = 3;
      } else {
        this.showHalfDay = true;
      }
    }
    else {
      this.showHalfDay = true;
      this.showsaveRequest = false;
    }
  }

}
