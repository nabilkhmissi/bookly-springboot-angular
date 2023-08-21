import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestSellersPageComponent } from './best-sellers-page.component';

describe('BestSellersPageComponent', () => {
  let component: BestSellersPageComponent;
  let fixture: ComponentFixture<BestSellersPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BestSellersPageComponent]
    });
    fixture = TestBed.createComponent(BestSellersPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
