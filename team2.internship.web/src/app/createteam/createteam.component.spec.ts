import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateteamComponent } from './createteam.component';

describe('CreateteamComponent', () => {
  let component: CreateteamComponent;
  let fixture: ComponentFixture<CreateteamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateteamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateteamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
