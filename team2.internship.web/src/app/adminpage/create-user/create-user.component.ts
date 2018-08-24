import { Component, OnInit, NgModule,Input,Output   } from '@angular/core';
import {Authority} from '../../models/authority.model';
import {User} from '../../models/user.model';
import {Position} from '../../models/position.model';
import {Team} from '../../models/team.model';
import {DataService} from '../shared/data.service';
import { Router } from '@angular/router';
import { EventEmitter } from '@angular/core';



@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  @Input() activePage: number;
  @Output() createUserComplete: EventEmitter<string> = new EventEmitter();
  selectedAuthority: Authority;
  authorities: Authority[];
  selectedPosition: Position;
  positions: Position[];
  selectedTeam: Team;
  teams: Team[];
  user: User=new User(null,null,null,null,null,null,null,null,null,null,null);


  constructor(private userService: DataService,private router: Router) {}

  ngOnInit() {
    this.getPositions();
    this.getAuthorities();
    this.getTeams();
    console.log(this.activePage);
  }

  getAuthorities(): void {
    this.userService.getAuthorities().subscribe(r => this.authorities = r);
  }

  getTeams(): void {
    this.userService.getTeams().subscribe(t => this.teams = t);
  }

  getPositions(): void {
    this.userService.getPositions().subscribe(p => this.positions = p);
  }

  createUser(user?): void {
    this.user.position=this.selectedPosition;
    this.user.authority=this.selectedAuthority;
    this.user.team=this.selectedTeam;
    this.user.password="init";
    this.userService.create(this.user).subscribe(response => {

      console.log(JSON.stringify(response));
      //alert(response.firstName+" "+response.lastName+" was added");
      this.createUserComplete.emit("succesful");
    });
  }

   

  }



