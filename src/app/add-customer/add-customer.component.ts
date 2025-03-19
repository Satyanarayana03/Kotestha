import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../service/customer.service';
import { Customer } from '../model/Customer';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-addcustomer',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customerForm: FormGroup;
  msg: string = '';
  visible: boolean = false;

  constructor(private fb: FormBuilder, private customerService: CustomerService, private router: Router) {
    this.customerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('^\\d{10}$')]],
      address: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(100)]]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.customerForm.valid) {
      const customer: Customer = this.customerForm.value;
      this.customerService.registerCustomer(customer).subscribe(
        (data: Customer) => {
          this.msg = 'Customer added successfully';
          this.customerForm.reset();
          this.visible = true;
        },
        (error: any) => {
          this.msg = error.error;
          this.visible = true;
        }
      );
    }
  }

  cancel() {
    this.router.navigate(['/customers']);
  }

  showAll(): void {
    this.router.navigate(['customers']);
  }
}