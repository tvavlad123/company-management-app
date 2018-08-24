import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {DataService} from '../shared/data.service';

import {Department} from '../../models/department.model';
import {Skill} from '../../models/skill.model';
import {Language} from '../../models/language.model';

@Component({
  selector: 'app-auxinfo',
  templateUrl: './auxinfo.component.html',
  styleUrls: ['./auxinfo.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AuxinfoComponent implements OnInit {

  departments: Department[] = [];
  skills: Skill[] = [];
  languages: Language[] = [];
  dept: Department;
  skill: Skill;
  language: Language;
  addDept: Department = new Department(null,null);
  addSkill: Skill = new Skill(null,null);
  addLang: Language = new Language(null,null);

  constructor(private dataService: DataService) { }

  ngOnInit():void{
    this.dataService.getDepartments().subscribe(departments => this.departments = departments);
    this.dataService.getSkills().subscribe(skills => this.skills = skills);
    this.dataService.getLanguages().subscribe(languages => this.languages = languages);
  }

  onSelectDept(depart: Department):void{
    this.dept=depart;
    console.log(this.dept);
  }

  onSelectSkill(skill: Skill):void{
    this.skill=skill;
    console.log(this.skill);
  }

  onSelectLanguage(language: Language):void{
    this.language=language;
    console.log(this.language);
  }

  deleteDepartment(): void{
    console.log(this.dept);
    this.dataService.deleteDepartment(this.dept.id).subscribe(r=>{
      this.dataService.getDepartments().subscribe(departments => this.departments = departments);
      this.dept=null;
    }
  );
  }

  addDepartment(): void{
    console.log(this.addDept);
    this.dataService.addDepartment(this.addDept).subscribe(r=>{

   this.dataService.getDepartments().subscribe(departments => this.departments = departments);
   this.addDept = new Department(null,null);
  
  } );
  }

  deleteSkill(): void{
    console.log(this.skill);
    this.dataService.deleteSkill(this.skill).subscribe(r=>{
      this.dataService.getSkills().subscribe(skills => this.skills = skills);
      this.skill=null;
    }
  );
  }

  addSkillMethod(): void{
    console.log(this.addSkill);
    this.dataService.addSkill(this.addSkill).subscribe(r=>{

   this.dataService.getSkills().subscribe(skills => this.skills = skills);
   this.addSkill = new Skill(null,null);
  
  } );
  }

  deleteLanguage(): void{
    console.log(this.language);
    this.dataService.deleteLanguage(this.language).subscribe(r=>{
      this.dataService.getLanguages().subscribe(languages => this.languages = languages);
      this.language=null;
    }
  );
  }

  addLanguage(): void{
    console.log(this.addLang);
    this.dataService.addLanguage(this.addLang).subscribe(r=>{

   this.dataService.getLanguages().subscribe(languages => this.languages = languages);
   this.addLang = new Language(null,null);
  
  } );
  }

}
