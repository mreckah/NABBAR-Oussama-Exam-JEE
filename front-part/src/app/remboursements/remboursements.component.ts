import { Component, OnInit, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RemboursementService } from './remboursement.service';
import { Remboursement } from './remboursement.interface';

@Component({
  selector: 'app-remboursements',
  templateUrl: './remboursements.component.html',
  styleUrls: ['./remboursements.component.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule]
})
export class RemboursementsComponent implements OnInit {
  remboursements: Remboursement[] = [];
  error: string = '';
  loading: boolean = true;

  constructor(private remboursementService: RemboursementService) {
    console.log('RemboursementsComponent initialized');
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadRemboursements();
  }

  loadRemboursements(): void {
    console.log('loadRemboursements called');
    this.loading = true;
    this.error = '';
    
    this.remboursementService.getRemboursements().subscribe({
      next: (data) => {
        console.log('Raw data received:', data);
        if (Array.isArray(data)) {
          this.remboursements = data;
          console.log('Processed remboursements:', this.remboursements);
        } else {
          console.error('Received data is not an array:', data);
          this.error = 'Invalid data format received from server';
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error in loadRemboursements:', error);
        this.error = error.message || 'Error loading remboursements. Please try again later.';
        this.loading = false;
      },
      complete: () => {
        console.log('Request completed');
        this.loading = false;
      }
    });
  }

  deleteRemboursement(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce remboursement ?')) {
      this.remboursementService.deleteRemboursement(id).subscribe({
        next: () => {
          this.remboursements = this.remboursements.filter(r => r.id !== id);
        },
        error: (error) => {
          this.error = 'Erreur lors de la suppression du remboursement';
          console.error('Error deleting remboursement:', error);
        }
      });
    }
  }

  retry(): void {
    this.loadRemboursements();
  }
} 