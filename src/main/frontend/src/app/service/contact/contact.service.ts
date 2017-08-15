import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Contact} from '../../class/contact/Contact';

@Injectable()
export class ContactService {

  constructor(private http: Http) {
  }

  getContacts(): Observable<Contact[]> {
    const user = JSON.parse(localStorage.getItem('user'));
    const header = new Headers();
    header.append('Authorization', 'Basic ' + btoa(user.login + ':' + user.password));
    return this.http.get('api/contact/' + user.id, {
      headers: header
    })
      .map((res: Response) => res.json());
  }


  addContact(contactNew: Contact): Observable<Contact> {
    const user = JSON.parse(localStorage.getItem('user'));
    const header = new Headers();
    header.append('Authorization', 'Basic ' + btoa(user.login + ':' + user.password));
    return this.http.post('api/contact/', contactNew, {
      headers: header
    })
      .map((res: Response) => res.json());
  }

  deleteContact(id: string) {
    const user = JSON.parse(localStorage.getItem('user'));
    const header = new Headers();
    header.append('Authorization', 'Basic ' + btoa(user.login + ':' + user.password));
    return this.http.delete('api/contact/' + id, {
      headers: header
    })
      .map((res: Response) => res.json());
  }

  update(contactNew: Contact) {
    const user = JSON.parse(localStorage.getItem('user'));
    const header = new Headers();
    header.append('Authorization', 'Basic ' + btoa(user.login + ':' + user.password));
    return this.http.put('api/contact/', contactNew, {
      headers: header
    })
      .map((res: Response) => res.json());
  }
}
