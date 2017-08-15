import {Component, OnInit} from '@angular/core';
import {ButtonModule} from 'primeng/primeng';
import {Router} from '@angular/router';
import {UserService} from '../service/user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  model: any = {
    login: '',
    password: ''
  };

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


  constructor(private router: Router, private userService: UserService) {
  }

  loginUser() {
    this.userService.isConfirm(this.model.login, this.model.password)
      .subscribe(data => {
          debugger;
          if (data) {
            localStorage.setItem('user', JSON.stringify(this.model));
            this.router.navigate(['user']);
          } else {
            window.alert('incorrect');
          }
        },
        error2 => {
          window.alert('incorrect');
        });

  }

}
