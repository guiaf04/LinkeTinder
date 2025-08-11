import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="profile-container">
      <h1>Perfil - Em Desenvolvimento</h1>
      <p>Gerenciamento de perfil será implementado aqui.</p>
    </div>
  `,
  styles: [`
    .profile-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class ProfileComponent {
}
