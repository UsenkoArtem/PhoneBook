export class Contact {

  public id: string;
  public userId: string;
  public firstName: string;
  public lastName: string;
  public patronymic: string;
  public mobilephone: string;
  public phone: string;
  public address: string;
  public email: string;

  constructor() {
  }

  insert(model = {firstName: '', lastName: '', email: '', address: '', patronymic: '', phone: '', mobilephone: ''}) {
    this.firstName = model.firstName;
    this.lastName = model.lastName;
    this.patronymic = model.patronymic;
    this.mobilephone = model.mobilephone;
    this.phone = model.phone;
    this.address = model.address;
    this.email = model.email;
  }


}
