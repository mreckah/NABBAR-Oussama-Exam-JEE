import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CreditService } from './credit.service';
import { Credit } from './credit.interface';

declare var bootstrap: any;

@Component({
  selector: 'app-credits',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './credits.component.html',
  styleUrl: './credits.component.css'
})
export class CreditsComponent implements OnInit, AfterViewInit {
  credits: Credit[] = [];
  error: string = '';
  loading: boolean = false;
  selectedCredit: Credit | null = null;
  private modal: any;

  @ViewChild('creditDetailsModal') modalElement!: ElementRef;

  constructor(private creditService: CreditService) {
    console.log('CreditsComponent initialized');
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadCredits();
  }

  ngAfterViewInit() {
    // Initialize the modal after the view is initialized
    if (typeof window !== 'undefined') {
      try {
        this.modal = new bootstrap.Modal(this.modalElement.nativeElement);
        console.log('Modal initialized successfully');
      } catch (error) {
        console.error('Error initializing modal:', error);
      }
    }
  }

  loadCredits(): void {
    console.log('loadCredits called');
    this.loading = true;
    this.error = '';
    
    this.creditService.getCredits().subscribe({
      next: (data) => {
        console.log('Raw data received:', data);
        if (Array.isArray(data)) {
          this.credits = data;
          console.log('Processed credits:', this.credits);
        } else {
          console.error('Received data is not an array:', data);
          this.error = 'Invalid data format received from server';
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error in loadCredits:', error);
        if (error.status === 0) {
          this.error = 'Unable to connect to the server. Please check if the server is running at http://localhost:8080';
        } else {
          this.error = error.message || 'Error loading credits. Please try again later.';
        }
        this.loading = false;
      },
      complete: () => {
        console.log('Request completed');
        this.loading = false;
      }
    });
  }

  showDetails(credit: Credit): void {
    this.selectedCredit = credit;
    if (this.modal) {
      try {
        this.modal.show();
      } catch (error) {
        console.error('Error showing modal:', error);
      }
    }
  }

  retry(): void {
    console.log('Retrying to load credits...');
    this.loadCredits();
  }
}
