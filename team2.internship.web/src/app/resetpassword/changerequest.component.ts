import { Component, OnInit } from '@angular/core';


export class ChangeRequest {

    userId: number;
    currentPassword: string;
    newPassword: string; 
    public showChangePass: boolean = false;

    constructor (userId: number, currentPassword: string, newPassword: string){
        this.userId=userId;
        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
    }
}