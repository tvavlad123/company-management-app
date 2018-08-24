import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
declare var jwt_decode:any;
import './jwt-decode.min.js';

@Injectable()
export class AuthenticationService {
    private authUrl = 'http://localhost:9123/auth';
    private headers = new Headers({'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'http://localhost:4200'});
    
    constructor(private http: Http) {
        var jwt_decode:any;     
    }

    login(username: string, password: string): Observable<boolean> {
        return this.http.post(this.authUrl, JSON.stringify({username: username, password: password}), {headers: this.headers})
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let token = response.json() && response.json().token;
                if (token) {
                    // let id=this.getIdByUsername(username);
                     //    console.log(id);
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    // localStorage.setItem('currentUser', JSON.stringify({ username: username, id: id, token: token }));
                    this.getInfoFromToken(token);
                    // return true to indicate successful login
                    return true;
                } else {
                    // return false to indicate failed login
                    return false;
                }
            }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }

    private getInfoFromToken(token:String){
        let decodedToken = jwt_decode(token);
        localStorage.setItem('currentUser', JSON.stringify({ username: decodedToken.sub, id: decodedToken.id, authority: decodedToken.authority, 
            expiration: decodedToken.expiration, token: token }));
    }


    private getIdByUsername(username : string):Observable<number>{
        return this.http.get('http://localhost:9123/getidbyusername',username).map(res => res.json());
    }

    getToken(): String {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var token = currentUser && currentUser.token;
        // console.log(token);
        return token ? token : "";
      }

      getId(): number {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var id = currentUser && currentUser.id;
        return id ? id : "";
      }
      getId21(): number {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var id = currentUser && currentUser.id;
        return id ? id : "";
      }

        getUserId(): Observable<number> {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var id = currentUser && currentUser.id;
        return id ? id : "";
      }

      getUsername(): String {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var username = currentUser && currentUser.username;
        return username ? username : "";
      }

      getAuthority(): any {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var authority = currentUser && currentUser.authority;
        return authority ? authority : "";
      }

      getExpiration(): any {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var expiration = currentUser && currentUser.expiration;
        return expiration ? expiration : "";
      }

    isLoggedIn(): boolean {
        var token: String = this.getToken();
        return token && token.length > 0;
      }

    logout(): void {
        // clear token remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }


    private handleError(error: any): Promise<any> {
        console.log('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
