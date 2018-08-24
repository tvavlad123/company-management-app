import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeofficeComponent } from './homeoffice.component';

describe('HomeofficeComponent', () => {
  let component: HomeofficeComponent;
  let fixture: ComponentFixture<HomeofficeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeofficeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeofficeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
