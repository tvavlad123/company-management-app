import { Injectable } from "@angular/core";
import { User } from "app/models/userList.model";
import { SkillEpos } from "app/models/epolist.model";
import { LanguageEpos } from "app/models/epos.model";
import { UserSkill } from "app/models/userskillList.model";
import { UserLanguage } from "app/models/userlanguageList.model";

export class CompareService {
  deepEquals(x, y) {
    if (x === y) {
      return true; // if both x and y are null or undefined and exactly the same
    } else if (!(x instanceof Object) || !(y instanceof Object)) {
      return false; // if they are not strictly equal, they both need to be Objects
    } else if (x.constructor !== y.constructor) {
      // they must have the exact same prototype chain, the closest we can do is
      // test their constructor.
      return false;
    } else {
      for (const p in x) {
        if (!x.hasOwnProperty(p)) {
          continue; // other properties were tested using x.constructor === y.constructor
        }
        if (!y.hasOwnProperty(p)) {
          return false; // allows to compare x[ p ] and y[ p ] when set to undefined
        }
        if (x[p] === y[p]) {
          continue; // if they have the same strict value or identity then they are equal
        }
        if (typeof (x[p]) !== 'object') {
          return false; // Numbers, Strings, Functions, Booleans must be strictly equal
        }
        if (!this.deepEquals(x[p], y[p])) {
          return false;
        }
      }
      for (const p in y) {
        if (y.hasOwnProperty(p) && !x.hasOwnProperty(p)) {
          return false;
        }
      }
      return true;
    }
  }

  equals(user1: User, user2: User) {
    let skillLength: number;
    if (user1.userSkills.epoList.length > user2.userSkills.epoList.length) {
      skillLength = user1.userSkills.epoList.length;
    }
    else {
      skillLength = user2.userSkills.epoList.length;
    }
    for (let i: number = 0; i < skillLength; i++) {
      if (user1.userSkills.epoList[i] !== user2.userSkills.epoList[i]) {
        return false;
      }
    }
    let languageLength: number;
    if (user1.userLanguages.epos.length > user2.userLanguages.epos.length) {
      languageLength = user1.userLanguages.epos.length;
    }
    else {
      languageLength = user2.userLanguages.epos.length;
    }
    for (let i: number = 0; i < languageLength; i++) {
      if (user1.userLanguages.epos[i] !== user2.userLanguages.epos[i]) {
        return false;
      }
    }
    if (user1.id !== user2.id || user1.firstName !== user2.firstName || user1.lastName !== user2.lastName
      || user1.userName !== user2.userName || user1.password !== user2.password || user1.authority !== user2.authority ||
      user1.photoLocation !== user2.photoLocation || user1.position !== user2.position || user1.team !== user2.team) {
      return false;
    }
    return true;
  }

  createCopy(user2: User): User {
    let user1: User = new User(user2.id, user2.firstName, user2.lastName, user2.userName, user2.password, user2.authority, user2.photoLocation, user2.position, user2.team, new SkillEpos(new Array<UserSkill>()), new LanguageEpos(new Array<UserLanguage>()))
    for (let i: number = 0; i < user2.userSkills.epoList.length; i++) {
      user1.userSkills.epoList.push(user2.userSkills.epoList[i]);
    }
    for (let i: number = 0; i < user2.userLanguages.epos.length; i++) {
      user1.userLanguages.epos.push(user2.userLanguages.epos[i]);
    }
    return user1;
  }

  restore(user1: User, user2: User): void {
    user1.id = user2.id;
    user1.firstName = user2.firstName;
    user1.lastName = user2.lastName;
    user1.userName = user2.userName;
    user1.password = user2.password;
    user1.authority = user2.authority;
    user1.photoLocation = user2.photoLocation;
    user1.position = user2.position;
    user1.team = user2.team;
    user1.userSkills = user2.userSkills;
    user1.userLanguages = user2.userLanguages;
  }
}