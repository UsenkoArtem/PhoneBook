import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../class/user/User';
import 'rxjs/add/operator/map';

@Injectable()
export class RegService {
  constructor(private http: Http) {
  }

  registration(user: User): Observable<User> {
    return this.http.post('api/registry', user)
      .map((res => {
        if (res.status === 200) {
          return res.json();
        } else {
          throw new Error();
        }
      }));
  }

  getLogin(value: any): Observable<string> {
    return this.http.get('api/registry?login=' + value)
      .map((res: Response) => res.json());
  }

}
