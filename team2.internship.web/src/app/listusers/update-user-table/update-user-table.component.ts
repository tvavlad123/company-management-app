import { Component, OnInit, Input, Output } from '@angular/core';
import { User } from "app/models/userList.model";
import { Authority } from "app/models/authority.model";
import { Team } from "app/models/team.model";
import { Department } from "app/models/department.model";
import { UserSkill } from "app/models/userskillList.model";
import { UserLanguage } from "app/models/userlanguageList.model";
import { Position } from "app/models/position.model";
import { ListusersComponent } from "app/listusers/listusers.component";
import { DataServiceList } from "app/adminpage/shared/dataList.service";
import { Skill } from "app/models/skill.model";
import { Language } from "app/models/language.model";
import { SkillLevel } from "app/models/skilllevel.model";
import { LanguageLevel } from "app/models/languagelevel.model";
import { EventEmitter } from '@angular/core';

@Component({
  selector: '[update-tr]',
  templateUrl: './update-user-table.component.html',
  styleUrls: ['./update-user-table.component.css']
})
export class UpdateUserTableComponent implements OnInit {

  @Input('update-tr') user: User;
  @Input() authorities: Authority[];
  @Input() positions: Position[];
  @Input() teams: Team[];
  @Input() departments: Department[];
  @Input() skills: Skill[];
  @Input() languages: Language[];
  @Input() skillLevels: SkillLevel[];
  @Input() languageLevels: LanguageLevel[];
  showRow: boolean = true;
  @Output() closeLineUser: EventEmitter<string> = new EventEmitter();

  constructor(private userService: DataServiceList) {
  }

  closeLine(event) {
    this.showRow = false;
    this.closeLineUser.emit("deselect")
  }

  ngOnInit() {
    if (this.user.authority.authorityType === "Team Coordinator") {
      this.userService.findTeamsWithoutCoord().subscribe(res => {
        this.teams = res;
        console.log("FOUND A COORD " + res)
      })
    }

  }

  testMethod(): void {
    this.showRow = true;
    if (this.user.authority.authorityType === "Team Coordinator") {
      this.userService.findTeamsWithoutCoord().subscribe(res => {
        this.teams = res;
        console.log("FOUND A COORD " + res)
      })
    }
    else {
      this.userService.getRemainingTeams(this.user.id).subscribe(res => this.teams = res);
    }
  }

  isSelected(): boolean {
    var temp = document.getElementById("" + this.user.id);
    if (temp.className === "table-line-part col-md-11 selected" && this.showRow === true) {
      return true;
    }
    else {
      return false;
    }
  }

}
