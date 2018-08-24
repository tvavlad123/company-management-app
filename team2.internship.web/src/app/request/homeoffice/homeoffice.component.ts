import { Component, OnInit, ViewEncapsulation, OnChanges } from '@angular/core';
import { DataService } from "app/adminpage/shared/data.service";

@Component({
  selector: 'app-homeoffice',
  templateUrl: './homeoffice.component.html',
  styleUrls: ['./homeoffice.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class HomeofficeComponent implements OnInit {

  startDate: Date;
  endDate: Date;
  halfday:boolean=false;

  constructor() {
  }


  ngOnInit(): void {

  }

save():void{
  console.log("Start date:"+this.startDate);
  console.log("End date:"+ this.endDate);
  console.log("Half day:"+this.halfday);
}

}
