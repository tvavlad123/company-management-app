import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HomeOfficeRequest } from "app/models/homeofficerequest.model";
import { VacationRequest } from "app/models/vacationrequest.model";
import { DataService } from "app/adminpage/shared/data.service";
import { Router } from "@angular/router";
import { AuthenticationService } from "app/security/authentication.service";

@Component({
  selector: 'app-pendingrequests',
  templateUrl: './pendingrequests.component.html',
  styleUrls: ['./pendingrequests.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PendingrequestsComponent implements OnInit {

  public HOrequests: HomeOfficeRequest[] = new Array<HomeOfficeRequest>();
  public Vrequests: VacationRequest[] = new Array<VacationRequest>();

  pendingHORequests: HomeOfficeRequest[] = new Array<HomeOfficeRequest>();
  pendingVRequests: VacationRequest[] = new Array<VacationRequest>();



  constructor(private requestService: DataService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.getAllHORequests();
    this.getAllVRequests();
    // this.getPendingRequests();

  }

  getAllHORequests() {
    this.requestService.findAllHomeOfficeRequestsUnresolved().subscribe(r => this.HOrequests = r);
  }

  getAllVRequests() {
    this.requestService.findAllVacationRequestsUnresolved().subscribe(r => this.Vrequests = r);
  }

  // getPendingRequests() {
  //   console.log(this.HOrequests);
  //   for (let i: number = 0; i < this.HOrequests.length; i++) {
  //     if (this.HOrequests[i].status_id.id === 2) {
  //       this.pendingHORequests.push(this.HOrequests[i]);
  //     }
  //   }

  //   for (let i: number = 0; i < this.Vrequests.length; i++) {
  //     if (this.Vrequests[i].status_id.id === 2) {
  //       this.pendingVRequests.push(this.Vrequests[i]);
  //     }
  //   }
  // }
}
