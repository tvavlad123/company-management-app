import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserTableComponent } from './update-user-table.component';

describe('UpdateUserTableComponent', () => {
  let component: UpdateUserTableComponent;
  let fixture: ComponentFixture<UpdateUserTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUserTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
