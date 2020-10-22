import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private baseUrl = 'http://localhost:7070/taco-loco/api';
  private getMenuUrl = this.baseUrl + '/menu/items';
  private placeOrderUrl = this.baseUrl + '/order';

  constructor(private http: HttpClient) { }

  getMenu(): Observable<any> {
    return this.http.get<object>(this.getMenuUrl);
  }

  placeOrder(data): Observable<any> {
    return this.http.post<object>(this.placeOrderUrl, data);
  }
}
