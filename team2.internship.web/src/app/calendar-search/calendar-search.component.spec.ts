import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarComponentSearch } from './calendar-search.component';

describe('CalendarComponentSearch', () => {
  let component: CalendarComponentSearch;
  let fixture: ComponentFixture<CalendarComponentSearch>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarComponentSearch ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarComponentSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
