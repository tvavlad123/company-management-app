import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamleadProfileComponent } from './teamlead-profile.component';

describe('TeamleadProfileComponent', () => {
  let component: TeamleadProfileComponent;
  let fixture: ComponentFixture<TeamleadProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamleadProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamleadProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
