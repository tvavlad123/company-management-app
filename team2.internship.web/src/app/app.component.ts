import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
 
})
export class AppComponent {
  title = 'Management Company';

}

export class createteam {
  id: number;
  name: string;
  department: string; 

}

export class updateteam {
  id: number;
  name: string;
  department: string; 
}

