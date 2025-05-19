import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CreditService } from '../credit.service';

@Component({
  selector: 'app-add-credit',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './add-credit.component.html',
  styleUrl: './add-credit.component.css'
})
export class AddCreditComponent {
  creditForm: FormGroup;
  error: string = '';
  success: string = '';

  constructor(
    private fb: FormBuilder,
    private creditService: CreditService,
    private router: Router
  ) {
    this.creditForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(0)]],
      description: ['', [Validators.required, Validators.minLength(3)]],
      type: ['', Validators.required],
      status: ['PENDING', Validators.required],
      date: [new Date().toISOString().split('T')[0], Validators.required]
    });
  }

  onSubmit(): void {
    if (this.creditForm.valid) {
      const credit = this.creditForm.value;
      this.creditService.createCredit(credit).subscribe({
        next: () => {
          this.success = 'Credit added successfully!';
          this.creditForm.reset();
          setTimeout(() => {
            this.router.navigate(['/credits']);
          }, 2000);
        },
        error: (error) => {
          this.error = 'Error adding credit. Please try again.';
          console.error('Error adding credit:', error);
        }
      });
    }
  }
} 