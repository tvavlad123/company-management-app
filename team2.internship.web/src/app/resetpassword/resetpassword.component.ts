import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../adminpage/shared/data.service';
import { User } from '../models/user.model';
import { ChangeRequest } from './changerequest.component';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  @Input() user: User;// = new User(null, null, null, null, null, null, null, null, null, null, null);
  currentpassword: string;
  newpassword: string;
  newpassword2: string;
  receivedResponse: number;
  passwordfail: boolean = false;

  passChanged: boolean = false;
  passNotChanged: boolean = false;
  passNotChanged2: boolean = false;

  constructor(private dataService: DataService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }


  // resetPassword(): void {
  //   this.dataService.resetPassword(this.authenticationService.getId()).subscribe(response => {
  //     console.log(this.authenticationService.getId())
  //     // alert(JSON.stringify(response));
  //   });
  // }


  changePassword(): void {
    if (this.newpassword === this.newpassword2) {
      let request: ChangeRequest = new ChangeRequest(this.authenticationService.getId(), this.currentpassword, this.newpassword)
      this.dataService.changePassword(request).subscribe(resp => {
        this.receivedResponse = resp
        if (this.receivedResponse === 1) {
          this.passChanged = true
        }
        else {
          this.passNotChanged = true;
        }
      });
    }
    else {
      this.passNotChanged2 = true;
    }
  }

}

