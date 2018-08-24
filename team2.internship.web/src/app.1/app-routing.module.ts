import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminpageComponent} from './adminpage/adminpage.component';
 
 
const routes: Routes = [
 // { path: '', redirectTo: '/adminpage', pathMatch: 'full' },
  { path: 'adminpage', component: AdminpageComponent},
  
];  
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}