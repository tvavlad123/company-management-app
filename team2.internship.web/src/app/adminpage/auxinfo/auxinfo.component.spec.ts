import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuxinfoComponent } from './auxinfo.component';

describe('AuxinfoComponent', () => {
  let component: AuxinfoComponent;
  let fixture: ComponentFixture<AuxinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuxinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuxinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
