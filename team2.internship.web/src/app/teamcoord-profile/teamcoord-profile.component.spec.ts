import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamcoordProfileComponent } from './teamcoord-profile.component';

describe('TeamcoordProfileComponent', () => {
  let component: TeamcoordProfileComponent;
  let fixture: ComponentFixture<TeamcoordProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamcoordProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamcoordProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
