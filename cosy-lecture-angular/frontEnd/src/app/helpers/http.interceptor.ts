import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';

//HttpInterceptor a intercept()une méthode pour inspecter et transformer les requêtes HTTP avant qu'elles ne soient envoyées au serveur.
//HttpRequestInterceptorimplémente HttpInterceptor. Nous allons ajouter withCredentials: true pour que le navigateur inclue Cookie dans l'en-tête Request (HttpOnly Cookie).

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    return next.handle(req);
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];
