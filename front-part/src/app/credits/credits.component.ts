import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CreditService } from './credit.service';
import { Credit } from './credit.interface';

@Component({
  selector: 'app-credits',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './credits.component.html',
  styleUrl: './credits.component.css'
})
export class CreditsComponent implements OnInit {
  credits: Credit[] = [];
  error: string = '';
  loading: boolean = false;

  constructor(private creditService: CreditService) {}

  ngOnInit(): void {
    this.loadCredits();
  }

  loadCredits(): void {
    this.loading = true;
    this.error = '';
    
    this.creditService.getCredits().subscribe({
      next: (data) => {
        this.credits = data;
        this.loading = false;
      },
      error: (error) => {
        this.error = error.message || 'Error loading credits. Please try again later.';
        this.loading = false;
        console.error('Error loading credits:', error);
      }
    });
  }

  retry(): void {
    this.loadCredits();
  }
}
