import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthorizederrorComponent } from './unauthorizederror.component';

describe('UnauthorizederrorComponent', () => {
  let component: UnauthorizederrorComponent;
  let fixture: ComponentFixture<UnauthorizederrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnauthorizederrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthorizederrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
