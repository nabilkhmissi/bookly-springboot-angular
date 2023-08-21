import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewestAddedComponent } from './newest-added.component';

describe('NewestAddedComponent', () => {
  let component: NewestAddedComponent;
  let fixture: ComponentFixture<NewestAddedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewestAddedComponent]
    });
    fixture = TestBed.createComponent(NewestAddedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
