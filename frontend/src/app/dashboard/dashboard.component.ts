import { Component, OnInit } from '@angular/core';
import { PersonService } from './person.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PersonContactCreateUpdateComponent } from './person-contact-create-update/person-contact-create-update.component';
import { isFormInvalid } from '../shared/form-utils';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [FormBuilder],
})
export class DashboardComponent implements OnInit {

  persons: any[] = [];
  personSelect: any = null;

  fgForm = false;
  fgModalCreateContact = false;

  form: FormGroup;

  cpfMask = '000.000.000-00';

  constructor(private personService: PersonService, private fb: FormBuilder, private dialog: MatDialog) { }

  ngOnInit() {
    console.log("opa")
    this.list();
  }

  list() {
    this.personService.list()
      .then(data => {
        this.persons = data;;
      })
      .catch(error => {
        console.log(error)
        alert(error.message);
      });
  }

  formBuildPerson() {
    this.fgForm = true;
    this.form = this.fb.group({
      name: [this.personSelect?.name, [Validators.required]],
      cpf: [this.personSelect?.cpf, [Validators.required]],
      birthDate: [this.personSelect?.birthDate ? this.getDateFormat(this.personSelect?.birthDate) : null, [Validators.required]],
      contactParamsList: [this.personSelect?.contacts ?
        this.personSelect?.contacts : [], [Validators.required]],
    });
  }

  getDateFormat(data: Date) {
    let currentDate: Date = null;
    if (data) {
      currentDate = new Date(data);
      currentDate.setHours(0);
      currentDate.setMinutes(0);
      currentDate.setSeconds(0);
      currentDate.setDate(currentDate.getDate() + 1);
    }
    return currentDate;
  }


  createPerson() {
    this.personSelect = null;
    this.formBuildPerson();
  }

  editPerson(personId: number) {
    this.personService.get(personId)
      .then(data => {
        this.personSelect = data;
        this.formBuildPerson();
      })
      .catch(error => {
        console.log(error)
        alert(error.message);
      });
  }

  deletePerson(personId: number) {
    this.personService.delete(personId)
      .then(data => {
        this.list();
      })
      .catch(error => {
        console.log(error)
        alert(error.message);
      });
  }

  createOrUpdateContact(personContact: any) {
    this.fgModalCreateContact = true;

    this.dialog.open(PersonContactCreateUpdateComponent, {
      data: personContact,
      disableClose: true
    }).afterClosed().subscribe(contact => {
      if (contact) {
        if (this.form.value.contactParamsList) {
          this.deleteContact(personContact);
        }
        this.form.controls.contactParamsList.setValue([...this.form.value.contactParamsList, contact]);
      }
    });
  }

  deleteContact(personContact: any) {
    const updatedContacts = this.form.value.contactParamsList.filter(contact => contact !== personContact);
    this.form.controls.contactParamsList.setValue(updatedContacts);
  }

  isCreateMode() {
    return this.personSelect == null;
  }

  isUpdateMode() {
    return this.personSelect != null;
  }


  save() {
    if (this.form.value.contactParamsList.length < 1) {
      alert("A Pessoa deve possuir ao menos um contato.");
      return;
    }

    if (isFormInvalid(this.form)) {
      return;
    }

    const currentDate = new Date();
    if (this.form.value.birthDate > currentDate) {
      alert("A Data de nascimento não pode ser uma data futura.");
      return;
    }

    this.doSubmit()
      .then(data => {
        this.list();
        this.fgForm = false;
      })
      .catch(error => {
        console.log(error)
        alert(error.message);
      });
  }

  doSubmit() {
    if (this.isCreateMode()) {
      return this.personService.create(this.form.value);
    } else {
      return this.personService.update(this.personSelect.id, this.form.value);
    }
  }

}
