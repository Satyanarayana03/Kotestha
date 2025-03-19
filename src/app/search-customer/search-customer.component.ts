import { Component } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { Customer } from '../model/Customer';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search-customer',
  imports: [CommonModule, FormsModule],
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.css']
})
export class SearchCustomerComponent {
  searchBy: string = 'email';
  email: string = '';
  id: number | null = null;
  customer: Customer | null = null;
  errorMsg: string = '';

  constructor(private customerService: CustomerService, private router: Router) {}

  searchCustomer(): void {
    if (this.searchBy === 'email' && this.email) {
      this.customerService.getCustomerByEmail(this.email).subscribe(
        (data: Customer) => {
          this.customer = data;
          this.errorMsg = '';
        },
        (error: any) => {
          this.customer = null;
          this.errorMsg = 'Customer not found';
        }
      );
    } else if (this.searchBy === 'id' && this.id !== null) {
      this.customerService.getCustomerById(this.id).subscribe(
        (data: Customer) => {
          this.customer = data;
          this.errorMsg = '';
        },
        (error: any) => {
          this.customer = null;
          this.errorMsg = 'Customer not found';
        }
      );
    } else {
      this.errorMsg = 'Please enter a valid email or ID';
    }
  }
}