import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseFilterComponent } from './browse-filter.component';

describe('BrowseFilterComponent', () => {
  let component: BrowseFilterComponent;
  let fixture: ComponentFixture<BrowseFilterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BrowseFilterComponent]
    });
    fixture = TestBed.createComponent(BrowseFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
