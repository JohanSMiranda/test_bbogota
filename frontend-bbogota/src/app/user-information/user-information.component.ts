import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-user-information',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './user-information.component.html',
  styleUrl: './user-information.component.css'
})
export class UserInformationComponent {
  cliente: any;

  constructor(private router: Router) {
    this.cliente = this.router.getCurrentNavigation()?.extras.state;
  }

  volver() {
    this.router.navigate(['/']);
  }
}
