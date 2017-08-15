import {Component, OnInit} from '@angular/core';
import {User} from '../class/user/User';
import {Router} from '@angular/router';
import {RegService} from '../service/reg/reg-service';
import {UserService} from '../service/user/user.service';

@Component({
  selector: 'app-register',
  templateUrl: 'reg.component.html'
})
export class RegisterComponent implements OnInit {
  model = {firstName: '', lastName: '', login: '', password: '', confirmPassword: '', patronymic: ''};

  constructor(public  regService: RegService,
              public  router: Router, public userService: UserService) {
  }

  ngOnInit(): void {
    const item = localStorage.getItem('user');
    if (item === null) {
      return;
    }
    const user = JSON.parse(item);
    this.userService.isConfirm(user.login, user.password)
      .subscribe(data => {
          if (!data) {
            return;
          } else {
            this.router.navigate(['user']);
          }
        },
        error2 => {
          return;
        });

  }

  register() {
    const user = new User(null, this.model.firstName, this.model.lastName, this.model.patronymic, this.model.login,
      this.model.password);
    this.regService.registration(user).subscribe(data => {
        localStorage.setItem('user', JSON.stringify(data));
        this.router.navigate(['user']);
      },
      error2 => {
      }
    );
  }
}
