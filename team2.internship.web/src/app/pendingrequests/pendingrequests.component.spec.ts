import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingrequestsComponent } from './pendingrequests.component';

describe('PendingrequestsComponent', () => {
  let component: PendingrequestsComponent;
  let fixture: ComponentFixture<PendingrequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingrequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingrequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
