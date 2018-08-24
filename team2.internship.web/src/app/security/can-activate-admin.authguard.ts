import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class CanActivateAuthGuardAdmin implements CanActivate {

  constructor(private router: Router, private authService: AuthenticationService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    var today ;
    today= new Date();   
    // console.log(today.getTime()); 
    // console.log(this.authService.getExpiration()); 
    if (this.authService.isLoggedIn()&&this.authService.getExpiration()>today.getTime()&&this.authService.getAuthority() == "ROLE_ADMIN") {
            // logged in so return true
            return true;
        }

        // not logged in so redirect to login page with the return url and return false
        this.router.navigate(['loginpage']);
        return false;
    }
}
