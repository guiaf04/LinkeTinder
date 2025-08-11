import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="register-container">
      <h1>Registro - Em Desenvolvimento</h1>
      <p>Componente de registro será implementado aqui.</p>
    </div>
  `,
  styles: [`
    .register-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class RegisterComponent {
}
