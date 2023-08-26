import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { IndexComponent } from './pages/index/index.component';
import { HeroComponent } from './components/hero/hero.component';
import { FeaturedComponent } from './components/featured/featured.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CheckoutFormComponent } from './components/checkout-form/checkout-form.component';
import { CheckoutItemComponent } from './components/checkout-item/checkout-item.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { BrowseFilterComponent } from './components/browse-filter/browse-filter.component';
import { BestSellersComponent } from './components/best-sellers/best-sellers.component';
import { BrowseComponent } from './pages/browse/browse.component';
import { NotificationComponent } from './components/notification/notification.component';
import { LoadingComponent } from './components/loading/loading.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthorsComponent } from './pages/authors/authors.component';
import { AuthorCardComponent } from './components/author-card/author-card.component';
import { CartComponent } from './components/cart/cart.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { WishlistItemComponent } from './components/wishlist-item/wishlist-item.component';
import { AuthorDetailsComponent } from './pages/author-details/author-details.component';
import { NewestAddedComponent } from './pages/newest-added/newest-added.component';
import { BestSellersPageComponent } from './pages/best-sellers-page/best-sellers-page.component';
import { FeaturedPageComponent } from './pages/featured/featured-page.component';
import { JwtInterceptor } from './interceptor/jwt.interceptor';
import { ErrorInterceptor } from './interceptor/error.interceptor';
import { BookCardComponent } from './components/book-card/book-card.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { FeaturesComponent } from './components/features/features.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    IndexComponent,
    HeroComponent,
    FeaturedComponent,
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HeroComponent,
    BestSellersComponent,
    BrowseComponent,
    BrowseFilterComponent,
    PaginationComponent,
    NotificationComponent,
    LoadingComponent,
    ProductDetailsComponent,
    LoginComponent,
    RegisterComponent,
    AuthorsComponent,
    AuthorCardComponent,
    CartComponent,
    CartItemComponent,
    WishlistComponent,
    WishlistItemComponent,
    AuthorDetailsComponent,
    NewestAddedComponent,
    BestSellersPageComponent,
    FeaturedPageComponent,
    CheckoutFormComponent,
    CheckoutItemComponent,
    CheckoutFormComponent,
    BookCardComponent,
    CheckoutComponent,
    ResetPasswordComponent,
    ChangePasswordComponent,
    FeaturesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
