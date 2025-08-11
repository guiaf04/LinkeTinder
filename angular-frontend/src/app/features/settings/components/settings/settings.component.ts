import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="settings-container"><h1>Configurações - Em Desenvolvimento</h1></div>`,
  styles: [`
    .settings-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class SettingsComponent {
}
