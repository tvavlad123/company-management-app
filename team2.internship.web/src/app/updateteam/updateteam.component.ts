import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Team} from '../models/team.model';
import {Department} from '../models/department.model'
import {DataService} from '../adminpage/shared/data.service';
@Component({
  selector: 'app-updateteam',
  templateUrl: './updateteam.component.html',
  styleUrls: ['./updateteam.component.css']
})
export class UpdateteamComponent implements OnInit {

  public teamid: number;
  public name: string;
  public department: string;
  teams: Team;
  public show: boolean = false;
  public team: Team=new Team(null, null, new Department(null, null));


  showTeams(): void{
    this.show=true;
  }
  hideControls(): void{
    this.show=false;
  }
  
  constructor(private dataService: DataService) { }

  ngOnInit() {
  }

  getTeams(): void{
    this.dataService.getTeams().subscribe()

  }
}
