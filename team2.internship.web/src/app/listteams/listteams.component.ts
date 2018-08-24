import { Component, OnInit, ViewEncapsulation, OnChanges } from '@angular/core';
import { Team } from '../models/team.model';
import { User } from '../models/user.model';
import { Department } from '../models/department.model'
import { DataService } from '../adminpage/shared/data.service';

@Component({
  selector: 'app-listteams',
  templateUrl: './listteams.component.html',
  styleUrls: ['./listteams.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ListteamsComponent implements OnInit {

  public teams: Team[] = [];
  public selectedTeam: Team;
  public show: boolean = false;
  public department: Department = new Department(null, null);
  public name: string;
  public myTeam: Team = new Team(null, "", new Department(0, ""));
  public depts: Department[] = new Array<Department>();
  public showCreate: boolean = false;
  public dept: Department = new Department(null, null);
  public teamMembers: User[] = [];
  public showMembers: boolean = false;
  public showUsers: boolean = false;
  public selectedMember: User;
  public showAddButton: boolean = false;
  public showRemoveButton: boolean = false;
  public allUsers: User[] = [];


  showControls(): void {
    console.log(this.selectedTeam.department);
    this.show = true;
    this.showMembers = false;
    this.showUsers = false;
    this.hideCreateControls();
    this.showAddButton=false;
    this.showRemoveButton=false;
  }

  hideControls(): void {
    this.show = false;
  }

  showCreateControls(): void {
    this.showCreate = true;
    this.showMembers = false;
    this.showUsers = false;
    this.show = false;
    this.showRemoveButton=false;
    this.showAddButton=false;
  }

  hideCreateControls(): void {
    this.showCreate = false;
  }

  showMembersControl(): void {
    this.showMembers = true;
    this.showUsers = false;
    this.hideCreateControls();
    this.showAddButton=false;
    this.showRemoveButton=true;
  }

  showUsersControl(): void {
    this.showUsers = true;
    this.showMembers = false;
    this.hideCreateControls();
    this.showAddButton=true;
    this.showRemoveButton=false;
    
  }

  constructor(private dataService: DataService) { }

  onSelect(team: Team): void {
    if (this.selectedTeam != team) {
      this.showMembers = false;
    }
    this.showRemoveButton = false;
    this.showAddButton = false;
    this.selectedTeam = team;
    this.selectedMember = null;

    this.viewTeam();
  }

  onMemberSelect(teamMember: User): void {
    this.selectedMember = teamMember;
    this.showAddButton = false;
    this.showRemoveButton = true;

  }

  onUserSelect(user: User): void {
    this.selectedMember = user;
    this.showAddButton = true;
    this.showRemoveButton = false;

  }

  ngOnInit(): void {
    this.dataService.getTeams().subscribe(r => this.teams = r);
    this.getDepartments();
  }

  getDepartments(): void {
    this.dataService.getDepartments().subscribe(r => this.depts = r)
  }

  deleteTeam(): void {
    this.dataService.deleteTeam(this.selectedTeam).subscribe(r => {
      console.log(r + " POATE");
      this.dataService.getTeams().subscribe(r => this.teams = r);
      this.selectedTeam = null;
    });

  }

  onKey(event: any) {
    console.log("IT WORKS");
  }


  createTeam(team?): void {
    this.myTeam.name = this.name;
    this.myTeam.department.id = this.department.id;
    this.myTeam.department.name = this.department.name;
    console.log(this.myTeam);
    console.log(this.department);
    this.dataService.createTeam(this.myTeam).subscribe(response => {
    this.dataService.getTeams().subscribe(r => this.teams = r);
    });
    this.hideCreateControls();
    this.showAddButton = false;
    this.showRemoveButton=false;
  }

  updateTeam(): void {
    console.log(this.selectedTeam);
    console.log(this.selectedTeam.department);
    this.dataService.createTeam(this.selectedTeam).subscribe(response => {
      //alert(JSON.stringify(response));
      this.dataService.getTeams().subscribe(r => this.teams = r);
    });
    this.hideControls();
    this.showAddButton=false;
    this.showRemoveButton=false;
    }

  viewTeam(): void {
    this.showMembersControl();
    this.showAddButton = false;
    this.showRemoveButton = false;
    this.dataService.getUsersByTeam(this.selectedTeam).subscribe(response => {
      this.teamMembers = response;
    });
    this.hideControls();
    this.hideCreateControls();
  }

    viewAllMembers(): void {
    this.showUsersControl();
    this.showAddButton = false;
    this.showRemoveButton = false;
    this.selectedMember = null;
    this.dataService.getNonMembers(this.selectedTeam.id).subscribe(response => {
      this.teamMembers = response;
    });
    this.hideControls();
    this.hideCreateControls();
   }

   removeMember(): void {
    this.dataService.deleteMember(this.selectedMember.id).subscribe(r => {
      this.dataService.getNonMembers(this.selectedTeam.id).subscribe(response => {
        this.allUsers = response;
      })
      this.dataService.getUsersByTeam(this.selectedTeam).subscribe(response => {
        this.teamMembers = response;
      })
      this.selectedMember = null;
    });

  }

  searchMembers(): void {
    console.log(this.name);
    if (this.name.length >0){
      this.dataService.getUsersByName(this.name).subscribe(gotMembers => this.teamMembers = gotMembers);
    }
    else {
      this.dataService.getUsers1().subscribe(response => {
      this.teamMembers = response;
    });
    }
  
  }

  onKeyUp(): void {
    this.searchMembers();
  }

  addUserToTeam(): void {
    let paramList: number[] = new Array<number>();
    paramList.push(this.selectedMember.id);
    paramList.push(this.selectedTeam.id);
    console.log(paramList);
    this.dataService.addUserToTeam(paramList).subscribe();
    this.viewTeam();
  }

}