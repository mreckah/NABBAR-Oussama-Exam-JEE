import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ActionComponent } from './action/action.component';
import { CreditsComponent } from './credits/credits.component';
import { AddCreditComponent } from './credits/add-credit/add-credit.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'action1', component: ActionComponent },
  { path: 'credits', component: CreditsComponent },
  { path: 'credits/add', component: AddCreditComponent }
];
