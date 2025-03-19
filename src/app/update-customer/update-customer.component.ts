import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../model/Customer';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updatecustomer',
  imports: [CommonModule, FormsModule],
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent {

  msg: string = '';
  loading: boolean = false;

  @Input() customer: Customer;
  customer1: Customer;

  constructor(private service: CustomerService, private router: Router) {
    this.customer = new Customer();
    this.customer1 = new Customer();
  }

  showAll() {
    this.router.navigate(['all']);
  }

  updateCustomer() {
    this.loading = true;
    this.service.updateCustomer(this.customer).subscribe(
      data => {
        this.msg = "Record Updated Successfully";
        this.customer1 = data;
        this.loading = false;
      },
      error => {
        this.msg = `Error: ${error.error}`;
        this.loading = false;
      }
    );
  }

  cancel() {
    this.customer = new Customer();
    this.msg = '';
  }
}