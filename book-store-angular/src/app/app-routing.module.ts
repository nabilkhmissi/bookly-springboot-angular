import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { BrowseComponent } from './pages/browse/browse.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { AuthorDetailsComponent } from './pages/author-details/author-details.component';
import { FeaturedPageComponent } from './pages/featured/featured-page.component';
import { NewestAddedComponent } from './pages/newest-added/newest-added.component';
import { BestSellersPageComponent } from './pages/best-sellers-page/best-sellers-page.component';
import { AuthorsComponent } from './pages/authors/authors.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';

const routes: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'books/browse', component: BrowseComponent },
  { path: 'books/details/:id', component: ProductDetailsComponent },
  { path: 'authors/details/:id', component: AuthorDetailsComponent },
  { path: 'books/featured', component: FeaturedPageComponent },
  { path: 'books/newest', component: NewestAddedComponent },
  { path: 'books/best-sellers', component: BestSellersPageComponent },
  { path: 'authors', component: AuthorsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
