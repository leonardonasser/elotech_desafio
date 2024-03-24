import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DragDropModule } from '@angular/cdk/drag-drop';

import { MatButtonToggleModule } from '@angular/material/button-toggle';

import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatDividerModule } from '@angular/material/divider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardContent, MatCardModule } from '@angular/material/card';
import { NgxMaskModule } from 'ngx-mask';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';


const publicModules = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  RouterModule,
  DragDropModule,
  MatPaginatorModule,
  MatTableModule,
  MatSortModule,
  MatCheckboxModule,
  MatIconModule,
  MatButtonModule,
  MatMenuModule,
  FormsModule,
  MatTooltipModule,
  ReactiveFormsModule,
  MatSelectModule,
  MatButtonToggleModule,
  MatDialogModule,
  MatInputModule,
  MatRadioModule,
  MatDividerModule,
  MatTabsModule,
  MatCardModule,
  MatDatepickerModule,
  MatNativeDateModule
];


@NgModule({
  declarations: [
  ],
  imports: [
    ...publicModules,
    NgxMaskModule.forRoot(),
  ],
  exports: [
    ...publicModules,
    NgxMaskModule,
  ],
})
export class SharedModule { }
