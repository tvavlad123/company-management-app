import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DataService } from '../adminpage/shared/data.service';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AdminpageComponent implements OnInit {

  constructor(private dataService: DataService) { }

  activeAnchor: number = 1;

  setActiveAnchor(anchorId: number) {
    this.activeAnchor = anchorId;
  }

  listUsersSwitch: boolean = false;
  listTeamsSwitch: boolean = false;

  showListUsers(){
    this.listUsersSwitch = !this.listUsersSwitch;
  }
  showListTeams(){
    this.listTeamsSwitch = !this.listTeamsSwitch;
  }

  ngOnInit() {
    // this.dataService.checkAdmin().subscribe(res=> console.log("AR TREBUI SA MEARGA"));
    this.setActiveAnchor(3);
  }

  createUserComplete(event) {
    this.setActiveAnchor(3);
  }
  
}
