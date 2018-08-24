import { Component, OnInit, ViewEncapsulation, ErrorHandler, Injectable, Injector} from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-unauthorizederror',
  templateUrl: './unauthorizederror.component.html',
  styleUrls: ['./unauthorizederror.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UnauthorizederrorComponent implements OnInit, ErrorHandler {

  public logError( error: any ) : void {}
  private http: Http;

  constructor(
    private router: Router,
    private injector: Injector) { }

  ngOnInit() {
  }

  handleError(error){


}


}
