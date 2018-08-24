import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { ListteamsComponent } from './listteams/listteams.component';
import { DatePipe } from '@angular/common';
import { ListusersComponent } from './listusers/listusers.component';
import { CreateteamComponent } from './createteam/createteam.component';
import { UpdateteamComponent } from './updateteam/updateteam.component';
import { CreateUserComponent } from './adminpage/create-user/create-user.component';
import { UpdateUserComponent } from './listusers/update-user/update-user.component';

import { DataService } from './adminpage/shared/data.service';
import { DataServiceList } from './adminpage/shared/dataList.service';
import { AuxinfoComponent } from './adminpage/auxinfo/auxinfo.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { TeamprofileComponent } from './teamprofile/teamprofile.component';
import { UpdateUserTableComponent } from './listusers/update-user-table/update-user-table.component';

import { AuthenticationService } from './security/authentication.service';
import { CanActivateAuthGuard } from './security/can-activate.authguard';
import { CanActivateAuthGuardAdmin } from './security/can-activate-admin.authguard';
import { SearchUserComponent } from './search-user/search-user.component';
import { TeamleadProfileComponent } from './teamlead-profile/teamlead-profile.component';
import { TeamcoordProfileComponent } from './teamcoord-profile/teamcoord-profile.component';
import { HomeOfficeRequestComponent } from './homeofficerequest/homeofficerequest.component';
import { VacationRequestComponent } from './vacationrequest/vacationrequest.component';
import { RequestsComponent } from './requests/requests.component';

import { CalendarComponent } from './calendar/calendar.component';
import { CalendarComponentSearch } from './calendar-search/calendar-search.component';
import { CalendarcreatereqComponent } from './calendarcreatereq/calendarcreatereq.component';
import { CompareService } from "app/adminpage/shared/compare.service";
import { UnauthorizederrorComponent } from './unauthorizederror/unauthorizederror.component';
import { PendingrequestsComponent } from 'app/pendingrequests/pendingrequests.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminpageComponent,
    LoginpageComponent,
    ListusersComponent,
    ListteamsComponent,
    CreateteamComponent,
    UpdateteamComponent,
    CreateUserComponent,
    UpdateUserComponent,
    AuxinfoComponent,
    ResetpasswordComponent,
    UserprofileComponent,
    TeamprofileComponent,
    SearchUserComponent,
    TeamleadProfileComponent,
    TeamcoordProfileComponent,
    HomeOfficeRequestComponent,
    VacationRequestComponent,
    RequestsComponent,
    CalendarComponent,
    CalendarcreatereqComponent,   
    UpdateUserTableComponent,
    UnauthorizederrorComponent,
    PendingrequestsComponent,
    CalendarComponentSearch
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule
  ],
  providers: [DataService, DataServiceList, AuthenticationService, CompareService, CanActivateAuthGuard, CanActivateAuthGuardAdmin, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }