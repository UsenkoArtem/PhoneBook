import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {
  InputTextModule, ButtonModule, ConfirmDialogModule, TooltipModule, DataTableModule, SharedModule,
  DialogModule
} from 'primeng/primeng';
import {LoginComponent} from './login-page/login.component';
import {FormsModule} from '@angular/forms';
import {routing} from './app.routing';
import {RegisterComponent} from './reg-page/reg.component';
import {AppComponent} from './app.component';
import {EqualsValidatorDirective} from './customValidation/equals-validator.directive';
import {ValidateLoginDirective} from './customValidation/validate-login.directive';
import {HttpModule} from '@angular/http';
import {RegService} from './service/reg/reg-service';
import {UserNavComponent} from './user-page/nav/user.nav.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from './service/user/user.service';
import {PageComponent} from './user-page/page/page.component';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Contact} from './class/contact/Contact';
import {ContactService} from './service/contact/contact.service';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    AppComponent,
    EqualsValidatorDirective,
    ValidateLoginDirective,
    UserNavComponent,
    PageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    InputTextModule,
    FormsModule,
    ButtonModule,
    ConfirmDialogModule,
    TooltipModule,
    DataTableModule,
    SharedModule,
    CommonModule,
    FormsModule,
    DialogModule,
    BrowserAnimationsModule,
    routing,
    NgbModule.forRoot()
  ],
  providers: [RegService, UserService, ContactService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
