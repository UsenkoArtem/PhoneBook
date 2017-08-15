import {Component, OnInit, Output} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {Contact} from '../../class/contact/Contact';
import {NgForm} from '@angular/forms';
import {ContactService} from '../../service/contact/contact.service';
import {error} from 'util';
import {Router} from '@angular/router';
import {UserNavComponent} from '../nav/user.nav.component';


@Component({
  selector: 'app-page',
  templateUrl: './page.component.html'
})
export class PageComponent implements OnInit {
  contact = {firstName: '', lastName: '', email: '', address: '', patronymic: '', phone: '', mobilephone: ''};
  selectedContact: Contact;
  newContact: boolean;
  contacts: Contact[] = [];
  load: boolean;
  login: string;
  userId = '';

  constructor(private contactService: ContactService, private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    const item = localStorage.getItem('user');
    if (item === null) {
      this.router.navigate(['login']);
      return;
    }
    const user = JSON.parse(item);
    this.userService.isConfirm(user.login, user.password)
      .subscribe(data => {
          localStorage.setItem('user', JSON.stringify(data));
          this.userId = data.id;
          this.contactService.getContacts().subscribe(data2 => this.contacts = data2);
        },
        error2 => {
          this.router.navigate(['login']);
        });
  }

  showDialogToAdd() {
    this.newContact = true;
    this.contact = {firstName: '', lastName: '', email: '', address: '', patronymic: '', phone: '', mobilephone: ''};
    this.load = true;
  }

  save() {
    const contactNew = new Contact();
    contactNew.firstName = this.contact.firstName;
    contactNew.lastName = this.contact.lastName;
    contactNew.patronymic = this.contact.patronymic;
    contactNew.address = this.contact.address;
    contactNew.phone = this.contact.phone;
    contactNew.mobilephone = this.contact.mobilephone;
    contactNew.email = this.contact.email;
    contactNew.userId = this.userId;
    if (this.newContact) {
      this.contactService.addContact(contactNew).subscribe(data => {
          this.contacts.push(data);
          const length2 = this.contacts.length;
          this.contacts = this.contacts.slice();
        }
        , error => {
        });
    } else {
      const contactIndex = this.findSelectetContactIndex();
      const selectedContact = this.contacts[contactIndex];
      contactNew.id = selectedContact.id;
      contactNew.userId = selectedContact.userId;
      this.contactService.update(contactNew)
        .subscribe(data => {
            this.contacts[contactIndex] = contactNew;
            this.contacts = this.contacts.slice();
          }
          , error => {
          });
    }
    this.contacts = this.contacts.slice();
    this.contact = {firstName: '', lastName: '', email: '', address: '', patronymic: '', phone: '', mobilephone: ''};
    this.load = false;
  }

  delete() {
    const index = this.findSelectetContactIndex();
    this.contactService.deleteContact(this.contacts[index].id).subscribe(data => {
      this.contacts = this.contacts.filter((val, i) => i !== index);
    });
    this.contact = {firstName: '', lastName: '', email: '', address: '', patronymic: '', phone: '', mobilephone: ''};
    this.load = false;
  }

  onRowSelect(event) {
    this.newContact = false;
    this.contact = this.cloneCar(event.data);
    this.load = true;
  }

  cloneCar(c: Contact): Contact {
    const book = new Contact();
    for (const prop in c) {
      book[prop] = c[prop];
    }
    return book;
  }

  findSelectetContactIndex(): number {
    return this.contacts.indexOf(this.selectedContact);
  }

  updateDialog(form: NgForm) {
    form.resetForm();

  }

}
