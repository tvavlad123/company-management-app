import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListteamsComponent } from './listteams.component';

describe('ListteamsComponent', () => {
  let component: ListteamsComponent;
  let fixture: ComponentFixture<ListteamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListteamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListteamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
