import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import {FormsModule} from '@angular/forms';
import {ModalComponent} from '../modal/modal.component';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [
    FormsModule,
    ModalComponent
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  tipoDocumento: string = '';
  numeroDocumento: string = '';
  errorMessage: string | null = null;
  showModal: boolean = false;

  constructor(private router: Router, private http: HttpClient) { }

  formularioValido(): boolean {
    const isNumeric = /^[0-9]+$/.test(this.numeroDocumento);
    return this.tipoDocumento !== '' && this.numeroDocumento.length >= 8 && this.numeroDocumento.length <= 11 && isNumeric;
  }

  buscarUsuario() {
    this.showModal = false;
    const payload = {
      type: this.tipoDocumento,
      documentNumber: this.numeroDocumento
    };

    this.http.post('/api/client', payload).pipe(
      catchError(error => {
        this.errorMessage = error?.error || 'Error al buscar el usuario. Intente nuevamente.';
        console.error('Error en la solicitud:', error);
        this.showModal = true;
        return of(null);
      })
    ).subscribe(response => {
      if (response) {
        this.router.navigate(['/user_information'], { state: response });
      }
    });
  }
}
