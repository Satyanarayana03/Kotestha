import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deletecustomer',
  imports: [CommonModule, FormsModule],
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent {

  customerID: number | null = null;
  msg: string = '';
  loading: boolean = false;

  constructor(private service: CustomerService, private router: Router) {}

  deleteCustomer(): void {
    if (this.customerID !== null) {
      this.loading = true;
      this.service.deleteCustomer(this.customerID).subscribe(
        () => {
          this.msg = "Customer deleted successfully";
          this.loading = false;
        },
        error => {
          this.msg = error.error;
          this.loading = false;
        }
      );
    } else {
      this.msg = "Please enter a valid Customer Id.";
    }
  }

  cancel(): void {
    this.customerID = null;
    this.msg = '';
  }
}