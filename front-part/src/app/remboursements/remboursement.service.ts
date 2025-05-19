import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Remboursement } from './remboursement.interface';

@Injectable({
  providedIn: 'root'
})
export class RemboursementService {
  private apiUrl = 'http://localhost:8080/api/remboursment';

  constructor(private http: HttpClient) {
    console.log('RemboursementService initialized with API URL:', this.apiUrl);
  }

  getRemboursements(): Observable<Remboursement[]> {
    console.log('Making GET request to:', this.apiUrl);
    return this.http.get<Remboursement[]>(this.apiUrl).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getRemboursement(id: number): Observable<Remboursement> {
    return this.http.get<Remboursement>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  createRemboursement(remboursement: Remboursement): Observable<Remboursement> {
    return this.http.post<Remboursement>(this.apiUrl, remboursement).pipe(
      catchError(this.handleError)
    );
  }

  updateRemboursement(id: number, remboursement: Remboursement): Observable<Remboursement> {
    return this.http.put<Remboursement>(`${this.apiUrl}/${id}`, remboursement).pipe(
      catchError(this.handleError)
    );
  }

  deleteRemboursement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else if (error.status === 0) {
      // Server is not reachable
      errorMessage = 'Unable to connect to the server. Please check if the server is running.';
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    
    console.error('Error in RemboursementService:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }
} 