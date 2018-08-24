import { Component, OnInit, ViewEncapsulation, OnChanges } from '@angular/core';
import { Team } from '../models/team.model';
import { User } from '../models/user.model';
import { Department } from '../models/department.model'
import { DataService } from '../adminpage/shared/data.service';

@Component({
  selector: 'app-teamprofile',
  templateUrl: './teamprofile.component.html',
  styleUrls: ['./teamprofile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class TeamprofileComponent implements OnInit {

  public teams: Team[] = [];
  public selectedTeam: Team;
  public show: boolean = false;
  public department: Department = new Department(null, null);
  public memberName: string;
  public userName: string;
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
  public selectedUser: User;



  showUpdateControls(): void {
    console.log(this.selectedTeam.department);
    this.show = true;
    this.showMembers = false;
    this.showUsers = false;
    this.showAddButton = false;
    this.showRemoveButton = false;
    this.hideCreateControls();

  }

  hideUpdateControls(): void {
    this.show = false;
  }

  showCreateControls(): void {
    this.showCreate = true;
    this.showMembers = false;
    this.showUsers = false;
    this.show = false;
    this.showRemoveButton = false;
    this.showAddButton = false;
    this.hideUpdateControls();
  }

  hideCreateControls(): void {
    this.showCreate = false;
  }

  showMembersControl(): void {
    this.showMembers = true;
    this.showUsers = false;
    this.showAddButton = true;
    this.showRemoveButton = true;
  }

  showUsersControl(): void {
    this.showUsers = true;
    this.showMembers = false;
    this.showAddButton = true;
    this.showRemoveButton = true;
  }

  constructor(private dataService: DataService) { }

  onSelect(team: Team): void {
    if (this.selectedTeam != team) {
      this.showMembers = false;
    }
    this.showRemoveButton = true;
    this.showAddButton = true;
    this.selectedTeam = team;
    this.selectedMember = null;
    this.selectedUser = null;
    this.showUsers = true;
    this.showMembers = true;
    // console.log(tam);
    this.viewAllMembers();
    this.viewTeam();
  }

  onMemberSelect(teamMember: User): void {
    this.selectedMember = teamMember;
    this.selectedUser = undefined;
    this.showAddButton = true;
    this.showRemoveButton = true;

  }

  onUserSelect(user: User): void {
    this.selectedUser = user;
    this.selectedMember = undefined;
    this.showAddButton = true;
    this.showRemoveButton = true;

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
      this.showRemoveButton = false;
      this.showAddButton = false;
      this.showUsers = false;
      this.showMembers = false;
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
    this.showRemoveButton = false;
  }

  updateTeam(): void {
    console.log(this.selectedTeam);
    console.log(this.selectedTeam.department);
    this.dataService.createTeam(this.selectedTeam).subscribe(response => {
      //alert(JSON.stringify(response));
      this.dataService.getTeams().subscribe(r => this.teams = r);
    });
    this.hideUpdateControls();
    this.showAddButton = false;
    this.showRemoveButton = false;
  }

  viewTeam(): void {
    this.showMembersControl();
    this.showUsers = true;
    this.show = true;
    this.showAddButton = true;
    this.showRemoveButton = true;
    console.log(this.selectedTeam)
    this.dataService.getUsersByTeam(this.selectedTeam).subscribe(response => {
      this.teamMembers = response;
    });
    this.hideUpdateControls();
    this.hideCreateControls();
  }

  viewAllMembers(): void {
    this.showUsersControl();
    this.showAddButton = true;
    this.showRemoveButton = true;
    this.selectedMember = null;
    this.dataService.getNonMembers(this.selectedTeam.id).subscribe(response => {
      this.allUsers = response;
      console.log(this.allUsers);
    });
    this.hideUpdateControls();
    this.hideCreateControls();
    console.log(this.allUsers+" <-- ALL USERS");
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
    console.log(this.memberName);
    if (this.memberName) {
      this.dataService.getUsersByNameFromTeam(this.memberName, this.selectedTeam)
        .subscribe(gotMembers => this.teamMembers = gotMembers);
    } else {
      this.dataService.getUsersByTeam(this.selectedTeam)
        .subscribe(gotMembers => this.teamMembers = gotMembers);
    }
  }

  searchUsers(): void {
    console.log(this.userName);
    if (this.userName) {
      this.dataService.getUsersByNameNotFromTeam(this.userName, this.selectedTeam)
        .subscribe(gotMembers => this.allUsers = gotMembers);
    } else {
      this.dataService.getNonMembers(this.selectedTeam.id)
        .subscribe(gotMembers => this.allUsers = gotMembers);
    }
  }


  onKeyUp(): void {
    this.searchMembers();
    this.searchUsers();
  }

  addUserToTeam(): void {
    let paramList: number[] = new Array<number>();
    paramList.push(this.selectedUser.id);
    paramList.push(this.selectedTeam.id);
    console.log(paramList);
    this.dataService.addUserToTeam(paramList).subscribe(r => {
      this.dataService.getNonMembers(this.selectedTeam.id).subscribe(response => {
        this.allUsers = response;
      })
      this.dataService.getUsersByTeam(this.selectedTeam).subscribe(response => {
        this.teamMembers = response;
      })
    });
  }

  showDeleteTeamConfDialog() {
    if (confirm("Are you sure you want to delete the team  " + this.selectedTeam.name)) {
      this.deleteTeam();
    }

  }
}