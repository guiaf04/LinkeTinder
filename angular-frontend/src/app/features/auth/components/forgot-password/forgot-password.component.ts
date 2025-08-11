import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="forgot-password-container">
      <h1>Esqueceu a Senha - Em Desenvolvimento</h1>
      <p>Componente de recuperação de senha será implementado aqui.</p>
    </div>
  `,
  styles: [`
    .forgot-password-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class ForgotPasswordComponent {
}
