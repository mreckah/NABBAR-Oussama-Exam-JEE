import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ActionComponent } from './action/action.component';
import { CreditsComponent } from './credits/credits.component';
import { AddCreditComponent } from './credits/add-credit/add-credit.component';
import { RemboursementsComponent } from './remboursements/remboursements.component';

export const routes: Routes = [
  { path: '', component: RemboursementsComponent },
  { path: 'home', component: HomeComponent },
  { path: 'action1', component: ActionComponent },
  { 
    path: 'credits', 
    children: [
      { path: '', component: CreditsComponent },
      { path: 'add', component: AddCreditComponent }
    ]
  },
  { path: 'remboursements', component: RemboursementsComponent }
];
