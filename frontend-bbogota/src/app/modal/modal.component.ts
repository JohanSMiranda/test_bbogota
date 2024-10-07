import { Component, Input } from '@angular/core';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent {
  @Input() showModal: boolean = false;
  @Input() errorMessage: string | null = null;

  closeModal() {
    this.showModal = false;
  }
}
