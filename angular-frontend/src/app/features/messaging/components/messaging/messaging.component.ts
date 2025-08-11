import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-messaging',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="messaging-container"><h1>Mensagens - Em Desenvolvimento</h1></div>`,
  styles: [`
    .messaging-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class MessagingComponent {
}
