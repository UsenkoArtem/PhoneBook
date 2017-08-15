import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login-page/login.component';
import {RegisterComponent} from './reg-page/reg.component';
import {UserNavComponent} from './user-page/nav/user.nav.component';
import {PageComponent} from './user-page/page/page.component';


const appRoutes: Routes = [
 // {path: 'registry', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegisterComponent},
  {path: 'user', component: PageComponent},
  // otherwise redirect to home
  {path: '**', redirectTo: 'login'}
];

export const routing = RouterModule.forRoot(appRoutes);
