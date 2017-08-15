import {AbstractControl, NG_VALIDATORS, Validator} from '@angular/forms';
import {Directive, forwardRef} from '@angular/core';
import {RegService} from '../service/reg/reg-service';

@Directive({
  selector: '[validateLogin][formControlName],[validateLogin] \n' +
  '    [formControl],[validateLogin][ngModel]',
  providers: [
    {provide: NG_VALIDATORS, useExisting: forwardRef(() => ValidateLoginDirective), multi: true}
  ]
})
export class ValidateLoginDirective implements Validator {
  private bool: any;
  constructor(public regService: RegService) {
  }
  validate(c: AbstractControl): { [p: string]: any } {
    const login = c;
    debugger
    if (login.value === null || login.value === undefined) {
      return null;
    }
    if (login.value.length < 3) {return null; }
    this.regService.getLogin(login.value).subscribe(data => {
      debugger;
      this.bool = data;
      if (this.bool === true) {
        login.setErrors({'validateLogin': data});
      } else {
        return null;
      }
    });
    return null;
  }
}
