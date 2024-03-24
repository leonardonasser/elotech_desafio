import { Component, OnInit, ViewChild, ElementRef, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { isFormInvalid } from 'src/app/shared/form-utils';

@Component({
  selector: 'app-person-contact-create-update',
  templateUrl: './person-contact-create-update.component.html',
  providers: [FormBuilder],
})
export class PersonContactCreateUpdateComponent implements OnInit {

  persons: any[] = [];

  fgCreate = false;
  fgModalCreateContact = false;

  formContact: FormGroup;

  cpfMask = '000.000.000-00';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private dialogRef: MatDialogRef<PersonContactCreateUpdateComponent>, private fb: FormBuilder) { }

  ngOnInit() {
    this.buildFormPersonContact();
  }

  buildFormPersonContact() {
    this.formContact = this.fb.group({
      id: [this.data?.id, []],
      name: [this.data?.name, [Validators.required]],
      phone: [this.data?.phone, [Validators.required]],
      email: [this.data?.email, [Validators.required, Validators.email]],
    });
  }

  isCreateMode() {
    return this.data == null;
  }

  isUpdateMode() {
    return this.data != null;
  }

  cancel() {
    this.dialogRef.close();
  }

  save() {

    if (isFormInvalid(this.formContact)) {
      return;
    }

    this.dialogRef.close(this.formContact.value);
  }

}
