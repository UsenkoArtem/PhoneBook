import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../class/user/User';


@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  isConfirm(username: string, password: string): Observable<User> {
    const header = new Headers();
    const user = {login: username, password: password};
    header.append('Authorization', 'Basic ' + btoa(username + ':' + password));
    return this.http.get('api/user/' + username, {
      headers: header,
    })
      .map((res: Response) => {
        if (res.status === 200) {
          return res.json();
        } else {
          throw new Error();
        }
      });
  }


}
