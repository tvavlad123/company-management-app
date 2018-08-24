import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Team} from '../models/team.model';
import {Department} from '../models/department.model'
import {DataService} from '../adminpage/shared/data.service';
@Component({
  selector: 'app-createteam',
  templateUrl: './createteam.component.html',
  styleUrls: ['./createteam.component.css']
})
export class CreateteamComponent implements OnInit {

  public teamid: number;
  
  
  teams: Team[]=[];
  
  
  public dept: Department=new Department(null, null);
  
public department: Department=new Department(null, null);
public name: string;
public myTeam: Team=new Team(null, "", new Department(0, ""));
depts: Department[]=new Array<Department>();
public show: boolean = false;
  showCreateControls(): void{
    this.show=true;
  }
  hideCreateControls(): void{
    this.show=false;
  }
  

  constructor( private dataService: DataService) { }



  ngOnInit() {
    this.getDepartments();
  }  
  createTeam2(team?):void{
    this.myTeam.name = this.myTeam.department.name;
    this.myTeam.department.id = parseInt(this.myTeam.department.id);
    
        this.dataService.createTeam(this.myTeam).subscribe(response => {

      alert(JSON.stringify(response));
    });
  }
  getDepartments(): void{
    this.dataService.getDepartments().subscribe(r=>this.depts=r)
    

  }

  

  createTeam(team?):void{
    this.myTeam.name = this.name;
    this.myTeam.department.id = this.department.id;
    this.myTeam.department.name = this.department.name;
    console.log(this.myTeam);
    console.log(this.department);
    this.dataService.createTeam(this.myTeam).subscribe(response => {

      alert(JSON.stringify(response));
    });
    this.hideCreateControls();
  }

}