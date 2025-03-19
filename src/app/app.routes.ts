import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { CustomerListComponent } from './show-all-customers/show-all-customers.component';
import { SearchCustomerComponent } from './search-customer/search-customer.component';
import { AppComponent } from './app.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { DeleteCustomerComponent } from './delete-customer/delete-customer.component';

export const routes: Routes = [
  { path: '', redirectTo: '/add', pathMatch: 'full' },
  { path: 'add', component: AddCustomerComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'search', component: SearchCustomerComponent },
  { path: 'update', component: UpdateCustomerComponent },
  { path: 'delete', component: DeleteCustomerComponent },
];
