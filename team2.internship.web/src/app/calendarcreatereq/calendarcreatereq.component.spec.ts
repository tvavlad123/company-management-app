import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarcreatereqComponent } from './calendarcreatereq.component';

describe('CalendarcreatereqComponent', () => {
  let component: CalendarcreatereqComponent;
  let fixture: ComponentFixture<CalendarcreatereqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarcreatereqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarcreatereqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
