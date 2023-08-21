import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistItemComponent } from './wishlist-item.component';

describe('WishlistItemComponent', () => {
  let component: WishlistItemComponent;
  let fixture: ComponentFixture<WishlistItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WishlistItemComponent]
    });
    fixture = TestBed.createComponent(WishlistItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
