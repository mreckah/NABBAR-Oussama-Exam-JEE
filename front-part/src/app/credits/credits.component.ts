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

  constructor(private creditService: CreditService) {}

  ngOnInit(): void {
    this.loadCredits();
  }

  loadCredits(): void {
    this.creditService.getCredits().subscribe({
      next: (data) => {
        this.credits = data;
      },
      error: (error) => {
        this.error = 'Error loading credits. Please try again later.';
        console.error('Error loading credits:', error);
      }
    });
  }
}
