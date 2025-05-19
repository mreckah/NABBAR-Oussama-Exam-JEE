import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { Credit } from './credit.interface';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8080/api/credits';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    console.log('CreditService initialized with API URL:', this.apiUrl);
  }

  getCredits(): Observable<Credit[]> {
    console.log('Making GET request to:', this.apiUrl);
    return this.http.get<Credit[]>(this.apiUrl, this.httpOptions).pipe(
      tap(response => console.log('Response received:', response)),
      retry(1),
      catchError(this.handleError)
    );
  }

  createCredit(credit: Credit): Observable<Credit> {
    console.log('Making POST request to:', this.apiUrl, 'with data:', credit);
    return this.http.post<Credit>(this.apiUrl, credit, this.httpOptions)
      .pipe(
        tap(response => console.log('Response received:', response)),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Full error object:', error);
    let errorMessage = 'An error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
      console.error('Client-side error:', error.error);
    } else if (error.status === 0) {
      // Server is not reachable
      errorMessage = 'Unable to connect to the server. Please check if the server is running at http://localhost:8080';
      console.error('Server unreachable error');
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      console.error('Server-side error:', {
        status: error.status,
        message: error.message,
        error: error.error
      });
    }
    
    console.error('Error in CreditService:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }
} 