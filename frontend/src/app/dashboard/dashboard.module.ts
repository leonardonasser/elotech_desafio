import { NgModule } from '@angular/core';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { PersonContactCreateUpdateComponent } from './person-contact-create-update/person-contact-create-update.component';

@NgModule({
  declarations: [
    DashboardComponent,
    PersonContactCreateUpdateComponent
  ],
  imports: [
    DashboardRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class DashboardModule { }
