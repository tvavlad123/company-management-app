import { Component, OnInit , ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../security/authentication.service';
import { DataService } from '../adminpage/shared/data.service';

@Component({
  moduleId: module.id,
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginpageComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';

  constructor(
      private router: Router,
      private authenticationService: AuthenticationService,
      private dataService: DataService) { }

  ngOnInit() {
      // reset login status
      this.authenticationService.logout();
  }

  login() {
      this.loading = true;
      this.authenticationService.login(this.model.username, this.model.password)
          .subscribe(result => {
              if (result === true) {
                  // login successful
                  if(this.authenticationService.getAuthority() == "ROLE_ADMIN")
                  this.router.navigate(['adminpage']);
                  else if(this.authenticationService.getAuthority() == "ROLE_TEAM_LEAD")
                   this.router.navigate(['teamleadprofile']);
                   else if(this.authenticationService.getAuthority() == "ROLE_TEAM_COORD")
                   this.router.navigate(['teamcoordprofile']);
                   else
                    this.router.navigate(['userprofile']);
              } else {
                  // login failed
                  this.error = 'Username or password is incorrect';
                  this.loading = false;
              }
          }, error => {
            this.loading = false;
            this.error = error;
          });
  }
}