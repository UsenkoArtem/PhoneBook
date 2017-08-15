import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../service/user/user.service';

@Component({
  selector: 'app-user-nav',
  templateUrl: './user.nav.component.html'
})
export class UserNavComponent {
  constructor(private router: Router) {
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }
}
