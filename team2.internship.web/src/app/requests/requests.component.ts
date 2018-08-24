import { Component, OnInit } from '@angular/core';
import { DataService } from "app/adminpage/shared/data.service";
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { VacationRequest } from "app/models/vacationrequest.model";
import { AuthenticationService } from "app/security/authentication.service";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  public homeOfficeRequest: HomeOfficeRequest;
  public allHORequests: HomeOfficeRequest[] = new Array<HomeOfficeRequest>();
  public allVRequests: VacationRequest[] = new Array<VacationRequest>();
  public showAcceptedVRequestTable: boolean = false;
  public showPendingVRequestTable: boolean = false;
  public showDeclinedVRequestTable: boolean = false;
  public showAcceptedHORequestTable: boolean = false;
  public showPendingHORequestTable: boolean = false;
  public showDeclinedHORequestTable: boolean = false;
  public showButtonsForHO: boolean = false;
  public showButtonsForV: boolean = false;
  public showVtable: boolean = false;
  public nrHO: number;
  public nrV: number;
  public showHORequestsAcceptedForUserPerYear:boolean=false;
  public showVRequestsAcceptedForUserPerYear:boolean=false;

  constructor(private dataService: DataService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getNrOfHORequestsAcceptedForUserPerYear();
    this.getNrOfVRequestsAcceptedForUserPerYear();
    console.log("test");
  }

  hideViewAcceptedVRequests() {
    this.showAcceptedVRequestTable = false;
  }

  hideViewAcceptedHORequests() {
    this.showAcceptedHORequestTable = false;
  }

  hideViewUnresolvedHORequests() {
    this.showPendingHORequestTable = false;
  }

  hideViewUnresolvedVRequests() {
    this.showPendingVRequestTable = false;
  }

  hideViewDeclinedHORequests() {
    this.showDeclinedHORequestTable = false;
  }

  hideViewDeclinedVRequests() {
    this.showDeclinedVRequestTable = false;
  }

  toggleV() {
    console.log("toggleV")
    this.showButtonsForV = true;
    this.showButtonsForHO = false;
    this.refreshVTables();
    this.showVRequestsAcceptedForUserPerYear=true;
    this.showHORequestsAcceptedForUserPerYear=false;
  }

  toggleHO() {
    console.log("toggleHO")
    this.showButtonsForHO = true;
    this.showButtonsForV = false;
    this.refreshHOTables();
    this.showVRequestsAcceptedForUserPerYear=false;
    this.showHORequestsAcceptedForUserPerYear=true;
  }

  refreshVTables() {
    if(this.showAcceptedHORequestTable===true)
    {
      this.showAcceptedHORequestTable=false;
      this.showAcceptedVRequestTable=false;
    }
    if(this.showPendingHORequestTable===true)
    {
      this.showPendingHORequestTable=false;
      this.showPendingVRequestTable=false;
    }
    if(this.showDeclinedHORequestTable===true)
    {
      this.showDeclinedHORequestTable=false;
      this.showDeclinedVRequestTable=false;
    }
  }

  refreshHOTables() {
    if(this.showAcceptedVRequestTable===true)
    {
      this.showAcceptedHORequestTable=false;
      this.showAcceptedVRequestTable=false;
    }
    if(this.showPendingVRequestTable===true)
    {
      this.showPendingHORequestTable=false;
      this.showPendingVRequestTable=false;
    }
    if(this.showDeclinedVRequestTable===true)
    {
      this.showDeclinedHORequestTable=false;
      this.showDeclinedVRequestTable=false;
    }


  }

  viewAcceptedVRequests() {
    this.dataService.findVacationRequestsAcceptedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allVRequests = response;
      this.showAcceptedVRequestTable = true;
    });
    this.showPendingVRequestTable = false;
    this.showDeclinedVRequestTable = false;
    this.hideViewAcceptedHORequests();
    this.hideViewDeclinedHORequests();
    this.hideViewUnresolvedHORequests();
  }

  viewAcceptedHORequests() {
    this.dataService.findHomeOfficeRequestsAcceptedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allHORequests = response;
      this.showAcceptedHORequestTable = true;
    });
    this.showPendingHORequestTable = false;
    this.showDeclinedHORequestTable = false;
    this.hideViewAcceptedVRequests();
    this.hideViewDeclinedVRequests();
    this.hideViewUnresolvedVRequests();
  }


  viewUnresolvedHORequests() {
    this.dataService.findHomeOfficeRequestsUnresolvedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allHORequests = response;
      this.showPendingHORequestTable = true;
    });
    this.showAcceptedHORequestTable = false;
    this.showDeclinedHORequestTable = false;
    this.hideViewUnresolvedVRequests();
    this.hideViewDeclinedVRequests();
    this.hideViewAcceptedVRequests();
  }

  viewUnresolvedVRequests() {
    this.dataService.findVacationRequestsUnresolvedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allVRequests = response;
      this.showPendingVRequestTable = true;
    });
    this.showAcceptedVRequestTable = false;
    this.showDeclinedVRequestTable = false;
    this.hideViewUnresolvedHORequests();
    this.hideViewAcceptedHORequests();
    this.hideViewDeclinedHORequests();
  }

  viewDeclinedHORequests() {
    this.dataService.findHomeOfficeRequestsDeclinedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allHORequests = response;
      this.showDeclinedHORequestTable = true;
    });
    this.showAcceptedHORequestTable = false;
    this.showPendingHORequestTable = false;
    this.hideViewDeclinedVRequests();
    this.hideViewAcceptedVRequests();
    this.hideViewUnresolvedVRequests();
  }

  viewDeclinedVRequests() {
    this.dataService.findVacationRequestsDeclinedForUser(this.authenticationService.getId()).subscribe(response => {
      this.allVRequests = response;
      this.showDeclinedVRequestTable = true;
    });
    this.showAcceptedVRequestTable = false;
    this.showPendingVRequestTable = false;
    this.hideViewDeclinedHORequests();
    this.hideViewAcceptedHORequests();
    this.hideViewUnresolvedHORequests();
  }

  getNrOfHORequestsAcceptedForUserPerYear(): void {
    let days:number;
    this.dataService.findNrOfHomeOfficeRequestsAcceptedForUserPerYear(this.authenticationService.getId()).subscribe(response => {
      this.nrHO = response;
      console.log(this.nrHO+" HOME OFFICE LEFT");
      days=response;
      if(isNaN(this.nrHO)){
        this.nrHO = 0;
      }
    });
  }

  getNrOfVRequestsAcceptedForUserPerYear(): void {
    let days:number;
    this.dataService.findNrOfVacationRequestsAcceptedForUserPerYear(this.authenticationService.getId()).subscribe(response => {
      this.nrV = response;
      console.log(this.nrV+" VACATION LEFT");
      days=response;
      if(isNaN(this.nrV)){
        this.nrV = 0;
      }
    });
    this.showHORequestsAcceptedForUserPerYear=true;
  }

}
