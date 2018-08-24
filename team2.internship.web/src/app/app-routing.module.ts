import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { ListteamsComponent } from './listteams/listteams.component';
import { ListusersComponent } from './listusers/listusers.component';
import { CreateteamComponent } from './createteam/createteam.component';
import { UpdateteamComponent } from './updateteam/updateteam.component';
import { CreateUserComponent } from './adminpage/create-user/create-user.component';
import { UpdateUserComponent } from './listusers/update-user/update-user.component';
import { AuxinfoComponent } from './adminpage/auxinfo/auxinfo.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { TeamprofileComponent } from './teamprofile/teamprofile.component';
import { CanActivateAuthGuard } from './security/can-activate.authguard';
import { CanActivateAuthGuardAdmin } from './security/can-activate-admin.authguard';
import { SearchUserComponent } from './search-user/search-user.component';
import { TeamleadProfileComponent } from './teamlead-profile/teamlead-profile.component';
import { TeamcoordProfileComponent } from './teamcoord-profile/teamcoord-profile.component';
import { HomeOfficeRequestComponent } from './homeofficerequest/homeofficerequest.component';
import { VacationRequestComponent } from './vacationrequest/vacationrequest.component';
import { RequestsComponent } from "app/requests/requests.component";
import {CalendarcreatereqComponent} from './calendarcreatereq/calendarcreatereq.component';
import { CalendarComponent } from './calendar/calendar.component';
import { UnauthorizederrorComponent } from './unauthorizederror/unauthorizederror.component';
import { PendingrequestsComponent } from "app/pendingrequests/pendingrequests.component";

const routes: Routes = [
  { path: '', redirectTo: '/loginpage', pathMatch: 'full' },
  { path: 'adminpage', component: AdminpageComponent, canActivate: [CanActivateAuthGuardAdmin] },
  { path: 'loginpage', component: LoginpageComponent,
    children:[]

},
  { path: 'listuserspage', component: ListusersComponent, canActivate: [CanActivateAuthGuardAdmin] },
  { path: 'createpage', component: CreateteamComponent, canActivate: [CanActivateAuthGuardAdmin] },
  { path: 'changepassword', component: ResetpasswordComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'searchuser/:id', component: SearchUserComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'teamleadprofile', component: TeamleadProfileComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'teamcoordprofile', component: TeamcoordProfileComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'homeofficerequest', component: HomeOfficeRequestComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'vacationrequest', component: VacationRequestComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'requests', component: RequestsComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'updatepage', component: UpdateteamComponent , canActivate: [CanActivateAuthGuard]},
  { path: 'listteamspage', component: ListteamsComponent , canActivate: [CanActivateAuthGuardAdmin]},
  { path: 'createuser', component: CreateUserComponent , canActivate: [CanActivateAuthGuardAdmin]},
  { path: 'updateuser/:id', component: UpdateUserComponent , canActivate: [CanActivateAuthGuard]},
  { path: 'auxinfo', component: AuxinfoComponent , canActivate: [CanActivateAuthGuard]},
  { path: 'resetpassword', component: ResetpasswordComponent , canActivate: [CanActivateAuthGuard]},
  { path: 'userprofile', component: UserprofileComponent , canActivate: [CanActivateAuthGuard]},
  { path: 'teamprofile', component: TeamprofileComponent , canActivate: [CanActivateAuthGuard]},
  {path: 'calendar', component: CalendarComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'calendarcreatereq', component: CalendarcreatereqComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'unauthorizederror', component: UnauthorizederrorComponent},
  { path: 'updatepage', component: UpdateteamComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'listteamspage', component: ListteamsComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'createuser', component: CreateUserComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'updateuser/:id', component: UpdateUserComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'auxinfo', component: AuxinfoComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'resetpassword', component: ResetpasswordComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'userprofile', component: UserprofileComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'teamprofile', component: TeamprofileComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'calendar', component: CalendarComponent, canActivate: [CanActivateAuthGuard] },
  { path: 'pendingrequests', component: PendingrequestsComponent, canActivate: [CanActivateAuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }