import { Component, OnInit, ViewEncapsulation, OnChanges,Output } from '@angular/core';
import { DataService } from "app/adminpage/shared/data.service";
import { VacationRequest } from "app/models/vacationrequest.model";
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { Status } from "app/models/status.model";
import { DatePipe } from '@angular/common';
import { AuthenticationService } from "app/security/authentication.service";
import { DomSanitizer } from '@angular/platform-browser';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-vacationrequest',
  templateUrl: './vacationrequest.component.html',
  styleUrls: ['./vacationrequest.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class VacationRequestComponent implements OnInit {
  
  public today: Date = new Date();
  public showsaveRequest: boolean;
  public vacationRequest: VacationRequest = new VacationRequest(null, this.authenticationService.getId(), null, null, null, null, new Status(1, null));
  public alert1: boolean = false;
  public alert2: boolean = false;
  public alert3: boolean = false;
  public alert4: boolean = false;
  public alert5: boolean = false;
  public alert6: boolean = false;
  public result: number;
  public inputImage: string;
  @Output() saveVac:  EventEmitter<string> = new EventEmitter();
   
  constructor(private dataService: DataService, private datePipe: DatePipe, private sanitizer: DomSanitizer, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    console.log(this.datePipe.transform(Date.now(), "yyyy-MM-dd:hh-mm-ss"))
    this.today = new Date(this.datePipe.transform(Date.now(), "yyyy-MM-dd"));
    console.log(this.today);
  }

  createVacationRequest() {
    if (this.vacationRequest.start_date === null) {
      // alert("Please set start date!");
      this.alert1 = true;
      return;
    }
    if (this.vacationRequest.end_date === null) {
      // alert("Please set end date!");
      this.alert1 = true;
      return;
    }
    console.log("today" + this.today)
    if (this.vacationRequest.start_date < this.today) {
      // alert("Start date cannot be in the past!");
      this.alert2 = true;
      return;
    }
    if (this.vacationRequest.start_date > this.vacationRequest.end_date) {
      // alert("Invalid date! Start date must be before end date or should equal end date :)");
      this.alert3 = true;
      return;
    }
    this.dataService.findNrOfAvailbleVacationDaysToTake(this.vacationRequest.user_id, this.vacationRequest.start_date, this.vacationRequest.end_date).subscribe(response => {
      console.log(JSON.stringify(response));
      this.result = response;
      if (response < 0) {
        // alert("Vacation request not sent! You do not have so many days left!");
        this.alert5 = true;
      } else {
        this.dataService.saveVacationRequest(this.vacationRequest).subscribe(response2 => {
          console.log(JSON.stringify(response2));
          if (response2.id > 0) {
            // alert("Vacation request succesfully sent! You still have " + response + " days left!");
            this.alert6 = true;
          }
          this.saveVac.emit("emitVac");
        });
      }
    });
  }

  startDateChanged() {
    console.log("hoooopaa");
    if (this.vacationRequest.start_date != null && this.vacationRequest.end_date != null) {
      this.showsaveRequest = true;
    }
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